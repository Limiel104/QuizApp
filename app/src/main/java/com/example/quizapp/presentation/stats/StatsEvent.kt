package com.example.quizapp.presentation.stats

sealed class StatsEvent {
    data class OnChipToggled(val value: String): StatsEvent()
}