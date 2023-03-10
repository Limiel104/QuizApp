package com.example.quizapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    val category: String,
    val correctAnswer: String,
    val incorrectAnswer1: String,
    val incorrectAnswer2: String,
    val incorrectAnswer3: String,
    val question: String,
    val difficulty: String,
    @PrimaryKey
    val id: String
)
