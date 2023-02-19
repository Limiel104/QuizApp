package com.example.quizapp.presentation.questions

data class TimerState(
    val currentTimeInSeconds: Int = 0,
    val displayedTime: String = "",
    val isTimerRunning: Boolean = false
)