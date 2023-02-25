package com.example.quizapp.domain.model

data class Result(
    val score: Int,
    val category: String,
    val difficulty: String,
    val time: Int,
    val date: Long
)