package com.example.quizapp.domain.use_case

import android.util.Log
import com.example.quizapp.data.mapper.toQuestionEntity
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuestionRepository

class AddQuestionToDbUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(questions: List<Question>) {
        Log.i("TAG LOCAL SAVE",questions.toString())
        questionRepository.insertQuestions(questions.map { it.toQuestionEntity() })
    }
}