package com.example.quizapp.presentation.questions

sealed class QuestionsEvent {
    data class SelectedAnswer(val value: String): QuestionsEvent()
    data class TimeDisplayedChange(val value: Int): QuestionsEvent()
    object DialogDismissed: QuestionsEvent()
}