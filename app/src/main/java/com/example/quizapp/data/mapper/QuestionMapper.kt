package com.example.quizapp.data.mapper

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.domain.model.Question

fun QuestionEntity.toQuestion(): Question {
    return Question(
        categoryId = categoryId,
        correctAnswer = correctAnswer,
        incorrectAnswersList = listOf(incorrectAnswer1,incorrectAnswer2,incorrectAnswer3),
        question = question,
        difficultyId = difficultyId
    )
}

fun Question.toQuestionEntity(): QuestionEntity {
    return QuestionEntity(
        categoryId = categoryId,
        correctAnswer = correctAnswer,
        incorrectAnswer1 = incorrectAnswersList[0],
        incorrectAnswer2 = incorrectAnswersList[1],
        incorrectAnswer3 = incorrectAnswersList[2],
        question = question,
        difficultyId = difficultyId
    )
}