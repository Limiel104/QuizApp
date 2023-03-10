package com.example.quizapp.domain.use_case

import android.util.Log
import com.example.quizapp.data.mapper.toQuestionEntity
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetQuestionsFromApiUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(
        category: String,
        difficulty: String
    ): Flow<List<Question>> = flow {
        try {
            val remoteQuestions = questionRepository.getQuestionsFromApi(category,difficulty)
            Log.i("TAG REMOTE",remoteQuestions.toString())
            emit(remoteQuestions)
        } catch (e: IOException) {
            Log.i("TAG",e.message.toString())
            emit(emptyList())
        }
    }
}