package com.example.quizapp.presentation.stats

import com.example.quizapp.domain.model.Result

data class StatsListState(
    val statsList: List<Result> = emptyList(),
    val category: String = "",
    val query: String = "",
    val lastSelected: String = ""
)