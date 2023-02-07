package com.example.quizapp.data.repository

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.data.local.QuizDao
import com.example.quizapp.data.remote.TriviaApi
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl @Inject constructor(
    private val api: TriviaApi,
    private val dao: QuizDao
): QuizRepository {

    override suspend fun insertQuestions(questionEntities: List<QuestionEntity>) {
        dao.insertQuestions(questionEntities)
    }

    override suspend fun clearQuestions() {
        dao.clearQuestions()
    }

    override suspend fun getAllQuestions(): List<QuestionEntity> {
        return dao.getAllQuestions()
    }

    override suspend fun getQuestionsFromApi(
        categoryId: String,
        difficultyId: String
    ): List<Question> {
        return api.getQuestions(categoryId, difficultyId)
    }

    override suspend fun getQuestionsFromDatabase(): List<QuestionEntity> {
        return dao.getAllQuestions()
    }
}