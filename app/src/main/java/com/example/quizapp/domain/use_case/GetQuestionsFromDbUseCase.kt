package com.example.quizapp.domain.use_case

import android.util.Log
import com.example.quizapp.data.mapper.toQuestion
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuestionsFromDbUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(
        category: String,
        difficulty: String
    ): Flow<List<Question>> = flow {
        val localQuestions = questionRepository.getQuestionsFromDatabase(category,difficulty)
        Log.i("TAG LOCAL",localQuestions.toString())
        if(localQuestions.isNotEmpty()) {
            emit(localQuestions.map { it.toQuestion() })
        }
    }
}