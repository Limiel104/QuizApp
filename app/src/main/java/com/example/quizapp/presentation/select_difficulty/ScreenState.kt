package com.example.quizapp.presentation.select_difficulty

data class ScreenState(
    val selectedCategory: String = "",
    val selectedDifficulty: String = "",
    val label: String = "",
    val iconId: Int = -1
)