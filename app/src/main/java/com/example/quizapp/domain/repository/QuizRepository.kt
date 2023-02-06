package com.example.quizapp.domain.repository

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.domain.model.Question

interface QuizRepository {

    suspend fun insertQuestions(questionEntities: List<QuestionEntity>)

    suspend fun clearQuestions()

    suspend fun getAllQuestions(): List<QuestionEntity>

    suspend fun getQuestionsFromApi(
        categoryId: String,
        difficultyId: String
    ): List<Question>

    suspend fun getQuestionsFromDatabase(): List<QuestionEntity>
}