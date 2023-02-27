package com.example.quizapp.presentation.questions

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.model.Result
import com.example.quizapp.domain.use_case.QuizUseCases
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.ui.theme.Red
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER
import com.example.quizapp.util.Constants.arts_category
import com.example.quizapp.util.Constants.arts_label
import com.example.quizapp.util.Constants.film_category
import com.example.quizapp.util.Constants.film_label
import com.example.quizapp.util.Constants.food_category
import com.example.quizapp.util.Constants.food_label
import com.example.quizapp.util.Constants.geography_category
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.history_category
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.knowledge_category
import com.example.quizapp.util.Constants.knowledge_label
import com.example.quizapp.util.Constants.music_category
import com.example.quizapp.util.Constants.music_label
import com.example.quizapp.util.Constants.science_category
import com.example.quizapp.util.Constants.science_label
import com.example.quizapp.util.Constants.society_category
import com.example.quizapp.util.Constants.society_label
import com.example.quizapp.util.Constants.sport_label
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _questionListState = mutableStateOf(QuestionListState())
    val questionListState: State<QuestionListState> = _questionListState

    private val _displayedQuestionState = mutableStateOf(DisplayedQuestionState())
    val displayedQuestionState: State<DisplayedQuestionState> = _displayedQuestionState

    private val _timerState = mutableStateOf(TimerState())
    val timerState: State<TimerState> = _timerState

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _questionListState.value = questionListState.value.copy(
                category = category
            )
        }

        savedStateHandle.get<String>("difficulty")?.let { difficulty ->
            _questionListState.value = questionListState.value.copy(
                difficulty = difficulty
            )
        }

        getQuestions()
        startTimer()
        setDisplayedTime(0)
    }

    fun onEvent(event: QuestionsEvent) {
        when(event) {
            is QuestionsEvent.SelectedAnswer -> {
                if(!_displayedQuestionState.value.isButtonLocked) {
                    preventMultiClick(true)
                    setAnswersColors(event.value)
                    setResult(event.value)
                    prepareNextQuestionOrFinishQuiz()
                }
            }
            is QuestionsEvent.TimeDisplayedChange -> {
                val currentTimeInSeconds = event.value+1
                _timerState.value = timerState.value.copy(
                    currentTimeInSeconds = currentTimeInSeconds
                )
                setDisplayedTime(currentTimeInSeconds)
            }
            is QuestionsEvent.DialogDismissed -> {
                _dialogState.value = dialogState.value.copy(
                    isDialogDismissed = true
                )
            }
        }
    }

    private fun getQuestions(
        category: String = _questionListState.value.category,
        difficulty: String = _questionListState.value.difficulty
    ) {
        val questionNumber = 1
        viewModelScope.launch {
            getQuestionsFromApi(category, difficulty)
            if(_questionListState.value.questionList.isEmpty()) {
                getQuestionsFromDb(category, difficulty)
                if(_questionListState.value.questionList.isEmpty()) {
                    activateNoQuestionsDialogAndStopTimer()
                }
                else {
                    setDisplayedQuestion(questionNumber)
                }
            }
            else {
                quizUseCases.addQuestionToDbUseCase(_questionListState.value.questionList)
                Log.i("TAG VM LOCAL SAVE", _questionListState.value.questionList.toString())
                setDisplayedQuestion(questionNumber)
            }
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

    private fun activateNoQuestionsDialogAndStopTimer() {
        _dialogState.value = dialogState.value.copy(
            isDialogActivated = true
        )
        _timerState.value = timerState.value.copy(
            isTimerRunning = false
        )
    }

    private fun getCategoryEntity(category: String): String {
        return when (category) {
            arts_category -> arts_label
            food_category -> food_label
            geography_category -> geography_label
            music_category -> music_label
            society_category -> society_label
            film_category -> film_label
            knowledge_category -> knowledge_label
            history_category -> history_label
            science_category -> science_label
            else -> sport_label
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
        val colorList = mutableListOf<Color>()

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

    private fun startTimer() {
        _timerState.value = timerState.value.copy(
            isTimerRunning = true
        )
    }

    private fun stopTimer() {
        _timerState.value = timerState.value.copy(
            isTimerRunning = false
        )
    }

    private fun setDisplayedTime(currentTimeInSeconds: Int) {
        val minutes = currentTimeInSeconds/60
        val seconds = currentTimeInSeconds - (minutes*60)

        val timeToDisplay = if(seconds<10) {
            "$minutes:0$seconds"
        } else {
            "$minutes:$seconds"
        }

        _timerState.value = timerState.value.copy(
            displayedTime = timeToDisplay
        )
    }

    private fun addResultToDb() {
        viewModelScope.launch {
            quizUseCases.addResultUseCase(
                Result(
                    score = _displayedQuestionState.value.result,
                    category = _questionListState.value.category,
                    difficulty = _questionListState.value.difficulty,
                    time = _timerState.value.currentTimeInSeconds,
                    date = System.currentTimeMillis()
                )
            )
        }
    }

    private fun prepareNextQuestionOrFinishQuiz() {
        Timer().schedule(700) {
            Log.i("TAG DELAY","0.7s")
            val wasLastQuestionAnswered = _displayedQuestionState.value.counter == QUESTIONS_NUMBER

            if(wasLastQuestionAnswered) {
                Log.i("TAG","LAST QUESTION WAS DISPLAYED")
                Log.i("TAG RESULT",_displayedQuestionState.value.result.toString())
                stopTimer()
                addResultToDb()
            }
            else {
                val nextQuestionNumber = _displayedQuestionState.value.counter + 1
                setDisplayedQuestion(nextQuestionNumber)
                preventMultiClick(false)
            }
        }
    }

    private fun preventMultiClick(isButtonLocked: Boolean) {
        _displayedQuestionState.value = displayedQuestionState.value.copy(
            isButtonLocked = isButtonLocked
        )
    }
}