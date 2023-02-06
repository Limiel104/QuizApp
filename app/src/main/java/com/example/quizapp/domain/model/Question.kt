package com.example.quizapp.domain.model

data class Question(
    val categoryId: String,
    val correctAnswer: String,
    val incorrectAnswersList: List<String>,
    val question: String,
    val difficultyId: String
)
