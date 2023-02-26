package com.example.quizapp.presentation.results

import androidx.compose.ui.graphics.Color
import com.example.quizapp.ui.theme.OffBlack

data class ResultsState(
    val correctAnswers: String = "",
    val incorrectAnswers: String = "",
    val time: String = "",
    val resultRating: String = "",
    val ratingColor: Color = OffBlack,
    val accuracy: String = "",
    val category: String = ""
)