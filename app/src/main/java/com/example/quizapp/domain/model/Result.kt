package com.example.quizapp.domain.model

data class Result(
    val id: Int,
    val score: Int,
    val category: String,
    val difficulty: String,
    val time: Int,
    val date: Long
)