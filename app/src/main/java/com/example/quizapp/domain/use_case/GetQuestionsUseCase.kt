package com.example.quizapp.domain.use_case

import com.example.quizapp.data.mapper.toQuestion
import com.example.quizapp.data.mapper.toQuestionEntity
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(
        category: String,
        difficulty: String
    ): Flow<List<Question>> = flow {
        try {
            val remoteQuestions = quizRepository.getQuestionsFromApi(category,difficulty)
            remoteQuestions.let { questions ->
                quizRepository.clearQuestions()
                quizRepository.insertQuestions(questions.map { it.toQuestionEntity() })
                emit(questions)
            }
        } catch (e: Exception) {
            val localQuestions = quizRepository.getQuestionsFromDatabase()
            if(localQuestions.isNotEmpty()) {
                emit(localQuestions.map { it.toQuestion() })
            }
        }
    }
}