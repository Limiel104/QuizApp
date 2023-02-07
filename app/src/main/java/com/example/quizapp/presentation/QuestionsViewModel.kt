package com.example.quizapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.use_case.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases
): ViewModel() {

    private val list:List<Question> = emptyList()

    private val _questions = MutableStateFlow(list)
    val questions = _questions.asStateFlow()

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            quizUseCases.getQuestionsUseCase("film_and_tv","medium").collect { questions ->
                _questions.value = questions
                Log.i("TAG",questions.toString())
            }
        }
    }
}