package com.example.quizapp.presentation.questions

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.use_case.QuizUseCases
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.ui.theme.Red
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _questionListState = MutableStateFlow(QuestionListState())
    private val questionListState = _questionListState.asStateFlow()

    private val _displayedQuestionState = mutableStateOf(DisplayedQuestionState())
    val displayedQuestionState: State<DisplayedQuestionState> = _displayedQuestionState

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _questionListState.value = _questionListState.value.copy(
                category = category
            )
        }

        savedStateHandle.get<String>("difficulty")?.let { difficulty ->
            _questionListState.value = _questionListState.value.copy(
                difficulty = difficulty
            )
        }

        getQuestions(_questionListState.value.category, _questionListState.value.difficulty)
    }

    fun onEvent(event: QuestionsEvent) {
        when(event) {
            is QuestionsEvent.SelectedAnswer -> {
                setAnswersColors(event.value)
                setResult(event.value)
                Timer().schedule(700) {
                    Log.i("TAG DELAY","0.5s")
                    val wasLastQuestionDisplayed = _displayedQuestionState.value.counter == 7

                    if(wasLastQuestionDisplayed) {
                        Log.i("TAG","LAST QUESTION WAS DISPLAYED")
                        Log.i("TAG RESULT",_displayedQuestionState.value.result.toString())
                    }
                    else {
                        val nextQuestionNumber = _displayedQuestionState.value.counter + 1
                        setDisplayedQuestion(nextQuestionNumber)
                    }
                }
            }
        }
    }

    private fun getQuestions(category: String, difficulty: String) {
        viewModelScope.launch {
            getQuestionsFromApi(category, difficulty)
            if(_questionListState.value.questionList.isEmpty()) {
                getQuestionsFromDb(category, difficulty)
            }
            val questionNumber = 1
            setDisplayedQuestion(questionNumber)
        }
    }

    private suspend fun getQuestionsFromApi(category: String, difficulty: String) {
        quizUseCases.getQuestionsFromApiUseCase(category, difficulty).collect { questions ->
            _questionListState.value = questionListState.value.copy(
                questionList = questions,
            )
            Log.i("TAG VM REMOTE", _questionListState.value.questionList.toString())
        }
    }

    private suspend fun getQuestionsFromDb(category: String, difficulty: String) {
        quizUseCases.getQuestionsFromDbUseCase(getCategoryEntity(category), difficulty).collect { questions ->
            _questionListState.value = questionListState.value.copy(
                questionList = questions,
            )
            Log.i("TAG VM LOCAL", _questionListState.value.questionList.toString())
        }
    }

    private fun getCategoryEntity(category: String): String {
        return when (category) {
            "art_and_literature" -> "Art & Literature"
            "film_and_tv" -> "Film & TV"
            "food_and_drink" -> "Food & Drink"
            "general_knowledge" -> "General Knowledge"
            "geography" -> "Geography"
            "history" -> "History"
            "music" -> "Music"
            "science" -> "Science"
            "society_and_culture" -> "Society & Culture"
            else -> "Sport & Leisure"
        }
    }

    private fun setDisplayedQuestion(questionNumber: Int) {
        val index = questionNumber-1
        _displayedQuestionState.value = displayedQuestionState.value.copy(
            question = _questionListState.value.questionList[index].question,
            answers = (_questionListState.value.questionList[index].incorrectAnswersList
                    + _questionListState.value.questionList[index].correctAnswer).shuffled(),
            answersColors = listOf(OffBlack, OffBlack, OffBlack, OffBlack),
            correctAnswer = _questionListState.value.questionList[index].correctAnswer,
            counter = questionNumber
        )
        Log.i("TAG SET QUESTION", _displayedQuestionState.value.toString())
    }

    private fun setAnswersColors(answer: String) {
        var colorList: List<Color> = emptyList()

        if(answer == _displayedQuestionState.value.correctAnswer) {
            val index = _displayedQuestionState.value.answers.indexOf(answer)

            for (i in 0..3) {
                colorList += if (index==i) {
                    Green
                } else {
                    OffBlack
                }
            }
        }
        else {
            val indexIncorrect = _displayedQuestionState.value.answers.indexOf(answer)
            val correct = _displayedQuestionState.value.correctAnswer
            val indexCorrect = _displayedQuestionState.value.answers.indexOf(correct)

            for (i in 0..3) {
                colorList += when(i) {
                    indexCorrect -> Green
                    indexIncorrect -> Red
                    else -> OffBlack
                }
            }
        }

        _displayedQuestionState.value = displayedQuestionState.value.copy(
            answersColors = colorList
        )
    }

    private fun setResult(answer: String) {
        val oldResult = _displayedQuestionState.value.result
        val isAnswerCorrect = answer == _displayedQuestionState.value.correctAnswer
        if(isAnswerCorrect) {
            _displayedQuestionState.value = displayedQuestionState.value.copy(
                result = oldResult + 1
            )
        }
    }
}