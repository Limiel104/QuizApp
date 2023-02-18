package com.example.quizapp.domain.use_case

import com.example.quizapp.data.repository.FakeQuizRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuestionsFromApiUseCaseTest {

    private lateinit var getQuestionsFromApiUseCase: GetQuestionsFromApiUseCase
    private lateinit var fakeQuizRepository: FakeQuizRepository

    @Before
    fun setUp() {
        fakeQuizRepository = FakeQuizRepository()
        getQuestionsFromApiUseCase = GetQuestionsFromApiUseCase(fakeQuizRepository)
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.category).isEqualTo("Geography")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory_returnFalse() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.category).isNotEqualTo("General Knowledge")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isEqualTo("Easy")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty_returnFalse() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isNotEqualTo("Hard")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategoryAndDifficulty() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.category).isEqualTo("Geography")
            assertThat(question.difficulty).isEqualTo("Easy")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategoryAndDifficulty_returnFalse() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.category).isNotEqualTo("History")
            assertThat(question.difficulty).isNotEqualTo("Medium")
        }
    }

    @Test
    fun returnListIsOfCorrectSize() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        assertThat(questions.size).isEqualTo(7)
    }
}