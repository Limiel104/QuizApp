package com.example.quizapp.presentation.questions

import com.example.quizapp.domain.model.Question

data class QuestionsState(
    val category: String = "",
    val difficulty: String = "",
    val questionList: List<Question> = emptyList()
)