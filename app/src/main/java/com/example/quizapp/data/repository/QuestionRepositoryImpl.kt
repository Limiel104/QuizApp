package com.example.quizapp.data.repository

import com.example.quizapp.data.local.QuestionDao
import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.data.remote.TriviaApi
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuestionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val api: TriviaApi,
    private val dao: QuestionDao
): QuestionRepository {

    override suspend fun insertQuestions(questionEntities: List<QuestionEntity>) {
        dao.insertQuestions(questionEntities)
    }

    override suspend fun getQuestionsFromApi(
        category: String,
        difficulty: String
    ): List<Question> {
        return api.getQuestions(category, difficulty)
    }

    override suspend fun getQuestionsFromDatabase(
        category: String,
        difficulty: String
    ): List<QuestionEntity> {
        return dao.getQuestions(category, difficulty)
    }
}