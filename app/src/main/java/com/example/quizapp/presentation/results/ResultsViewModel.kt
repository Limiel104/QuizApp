package com.example.quizapp.presentation.results

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.Red
import com.example.quizapp.ui.theme.Yellow
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER
import com.example.quizapp.util.Constants.average
import com.example.quizapp.util.Constants.bad
import com.example.quizapp.util.Constants.good
import com.example.quizapp.util.Constants.great
import com.example.quizapp.util.Constants.ok
import com.example.quizapp.util.Constants.perfect
import com.example.quizapp.util.Constants.very_good
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
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

        savedStateHandle.get<String>("category")?.let { category ->
            _resultsState.value = resultsState.value.copy(
                category = category
            )
        }
    }

    private fun calculateResults(correctAnswers: Int) {
        val rating = when(correctAnswers) {
            20 -> perfect
            18,19 -> great
            16,17 -> very_good
            14,15 -> good
            in 10..13 -> average
            in 6..9 -> ok
            else -> bad
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

        val timeToDisplay = if(seconds<10) {
            "$minutes:0$seconds"
        } else {
            "$minutes:$seconds"
        }

        _resultsState.value = resultsState.value.copy(
            time = timeToDisplay
        )
    }
}