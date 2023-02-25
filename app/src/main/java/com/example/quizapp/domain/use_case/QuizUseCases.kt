package com.example.quizapp.domain.use_case

data class QuizUseCases(
    val getQuestionsFromApiUseCase: GetQuestionsFromApiUseCase,
    val getQuestionsFromDbUseCase: GetQuestionsFromDbUseCase,
    val addQuestionToDbUseCase: AddQuestionToDbUseCase,
    val addResultUseCase: AddResultUseCase
)