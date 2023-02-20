package com.example.quizapp.presentation.results

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizapp.domain.use_case.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    init {
        savedStateHandle.get<Int>("result")?.let { result ->
            Log.i("TAG R RESULT",result.toString())
        }

        savedStateHandle.get<Int>("time")?.let { time ->
            Log.i("TAG R TIME",time.toString())
        }
    }
}