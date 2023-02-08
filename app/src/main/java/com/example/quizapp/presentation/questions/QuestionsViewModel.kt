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

        getQuestions(_questionState.value.category,_questionState.value.difficulty)
    }

    private fun getQuestions(category: String, difficulty: String) {
        viewModelScope.launch {
            quizUseCases.getQuestionsUseCase(category,difficulty).collect { questions ->
                _questionState.value = questionState.value.copy(
                    questionList = questions
                )
                Log.i("TAG",_questionState.value.questionList.toString())
            }
        }
    }
}