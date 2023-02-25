package com.example.quizapp.domain.repository

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.domain.model.Question

interface QuestionRepository {

    suspend fun insertQuestions(questionEntities: List<QuestionEntity>)

    suspend fun getQuestionsFromApi(
        category: String,
        difficulty: String
    ): List<Question>

    suspend fun getQuestionsFromDatabase(
        category: String,
        difficulty: String
    ): List<QuestionEntity>
}