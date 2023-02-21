package com.example.quizapp.presentation.results

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizapp.domain.use_case.QuizUseCases
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.Red
import com.example.quizapp.ui.theme.Yellow
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _resultsState = mutableStateOf((ResultsState()))
    val resultsState: State<ResultsState> = _resultsState
    init {
        savedStateHandle.get<Int>("result")?.let { correctAnswers ->
            calculateResults(correctAnswers)
        }

        savedStateHandle.get<Int>("time")?.let { time ->
            setTime(time)
        }
    }

    private fun calculateResults(correctAnswers: Int) {
        val rating = when(correctAnswers) {
            20 -> "Perfect"
            18,19 -> "Great"
            16,17 -> "Very Good"
            14,15 -> "Good"
            in 10..13 -> "Average"
            in 6..9 -> "OK"
            else -> "Bad"
        }

        val color = when(correctAnswers) {
            in 16..20 -> Green
            in 10..15 -> Yellow
            else -> Red
        }

        val percent = (correctAnswers.toFloat()/20)*100
        val df = DecimalFormat("#.#")
        val accuracy = df.format(percent)

        val incorrectAnswers = QUESTIONS_NUMBER - correctAnswers

        _resultsState.value = resultsState.value.copy(
            correctAnswers = correctAnswers.toString(),
            incorrectAnswers = incorrectAnswers.toString(),
            resultRating = rating,
            ratingColor = color,
            accuracy = accuracy,
        )
    }

    private fun setTime(currentTimeInSeconds: Int) {
        val minutes = currentTimeInSeconds/60
        val seconds = currentTimeInSeconds - (minutes*60)
        var timeToDisplay = ""

        timeToDisplay = if(seconds<10) {
            "$minutes:0$seconds"
        } else {
            "$minutes:$seconds"
        }

        _resultsState.value = resultsState.value.copy(
            time = timeToDisplay
        )
    }
}