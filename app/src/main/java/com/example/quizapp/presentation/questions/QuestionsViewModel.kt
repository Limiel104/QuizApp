package com.example.quizapp.presentation.questions

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.use_case.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _questionState = MutableStateFlow(QuestionsState())
    val questionState = _questionState.asStateFlow()

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _questionState.value = _questionState.value.copy(
                category = category
            )
        }

        savedStateHandle.get<String>("difficulty")?.let { difficulty ->
            _questionState.value = _questionState.value.copy(
                difficulty = difficulty
            )
        }

        getQuestions(_questionState.value.category, _questionState.value.difficulty)
    }

    private fun getQuestions(category: String, difficulty: String) {
        viewModelScope.launch {
            getQuestionsFromApi(category, difficulty)
            if(_questionState.value.questionList.isEmpty()) {
                getQuestionsFromDb(category, difficulty)
            }
        }
    }

    private suspend fun getQuestionsFromApi(category: String, difficulty: String) {
        quizUseCases.getQuestionsFromApiUseCase(category, difficulty).collect { questions ->
            _questionState.value = questionState.value.copy(
                questionList = questions
            )
            Log.i("TAG VM REMOTE", _questionState.value.questionList.toString())
        }
    }

    private suspend fun getQuestionsFromDb(category: String, difficulty: String) {
        quizUseCases.getQuestionsFromDbUseCase(getCategoryEntity(category), difficulty).collect { questions ->
            _questionState.value = questionState.value.copy(
                questionList = questions
            )
            Log.i("TAG VM LOCAL", _questionState.value.questionList.toString())
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
}