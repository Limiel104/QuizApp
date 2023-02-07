package com.example.quizapp.domain.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("category")
    val categoryId: String,
    @SerializedName("correctAnswer")
    val correctAnswer: String,
    @SerializedName("difficulty")
    val difficultyId: String,
    @SerializedName("incorrectAnswers")
    val incorrectAnswersList: List<String>,
    @SerializedName("question")
    val question: String,
)