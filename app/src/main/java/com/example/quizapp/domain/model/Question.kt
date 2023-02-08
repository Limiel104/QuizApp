package com.example.quizapp.domain.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("category")
    val category: String,
    @SerializedName("correctAnswer")
    val correctAnswer: String,
    @SerializedName("difficulty")
    val difficulty: String,
    @SerializedName("incorrectAnswers")
    val incorrectAnswersList: List<String>,
    @SerializedName("question")
    val question: String,
)