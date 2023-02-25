package com.example.quizapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultEntity(
    val score: Int,
    val category: String,
    val difficulty: String,
    val time: Int,
    val date: Long,
    @PrimaryKey
    val id: Int? = null
)