package com.example.quizapp.presentation.questions

import androidx.compose.ui.graphics.Color
import com.example.quizapp.ui.theme.OffBlack

data class DisplayedQuestionState(
    val question: String = "",
    val answers: List<String> = listOf("","","",""),
    val answersColors: List<Color> = listOf(OffBlack,OffBlack,OffBlack,OffBlack),
    val correctAnswer: String = "",
    val counter: Int = 0,
    val result: Int = 0,
    val isButtonLocked: Boolean = false
)