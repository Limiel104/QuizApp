package com.example.quizapp.domain.use_case

import com.example.quizapp.data.repository.FakeQuestionRepository
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.knowledge_label
import com.example.quizapp.util.Constants.medium_label
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuestionsFromApiUseCaseTest {

    private lateinit var getQuestionsFromApiUseCase: GetQuestionsFromApiUseCase
    private lateinit var fakeQuestionRepository: FakeQuestionRepository

    @Before
    fun setUp() {
        fakeQuestionRepository = FakeQuestionRepository()
        getQuestionsFromApiUseCase = GetQuestionsFromApiUseCase(fakeQuestionRepository)
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.category).isEqualTo(geography_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory_returnFalse() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.category).isNotEqualTo(knowledge_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isEqualTo(easy_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty_returnFalse() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isNotEqualTo(hard_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategoryAndDifficulty() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.category).isEqualTo(geography_label)
            assertThat(question.difficulty).isEqualTo(easy_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategoryAndDifficulty_returnFalse() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.category).isNotEqualTo(history_label)
            assertThat(question.difficulty).isNotEqualTo(medium_label)
        }
    }

    @Test
    fun returnListIsOfCorrectSize() = runBlocking {
        val questions = getQuestionsFromApiUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        assertThat(questions.size).isEqualTo(QUESTIONS_NUMBER)
    }
}