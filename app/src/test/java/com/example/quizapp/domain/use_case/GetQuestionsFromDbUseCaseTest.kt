package com.example.quizapp.domain.use_case

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.data.repository.FakeQuizRepository
import com.example.quizapp.domain.model.Question
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuestionsFromDbUseCaseTest {

    private lateinit var getQuestionsFromDbUseCase: GetQuestionsFromDbUseCase
    private lateinit var fakeQuizRepository: FakeQuizRepository

    @Before
    fun setUp() {
        fakeQuizRepository = FakeQuizRepository()
        getQuestionsFromDbUseCase = GetQuestionsFromDbUseCase(fakeQuizRepository)

        val questionsToInsert = mutableListOf<QuestionEntity>()

        for(i in 0..5) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = "Geography",
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = "Easy",
                )
            )
        }

        for(i in 6..10) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = "Society & Culture",
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = "Easy",
                )
            )
        }

        for(i in 11..15) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = "Society & Culture",
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = "Hard",
                )
            )
        }

        for(i in 16..20) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = "Geography",
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = "Medium",
                )
            )
        }

        questionsToInsert.shuffle()

        runBlocking {
            fakeQuizRepository.insertQuestions(questionsToInsert)
        }
    }

    @Test
    fun returnListElementsAreOfTypeQuestion() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question).isInstanceOf(Question::class.java)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.category).isEqualTo("Geography")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory_returnFalse() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.category).isNotEqualTo("General Knowledge")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isEqualTo("Easy")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty_returnFalse() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isNotEqualTo("Hard")
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategoryAndDifficulty() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
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
        val questions = getQuestionsFromDbUseCase(
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
        val questions = getQuestionsFromDbUseCase(
            category = "Geography",
            difficulty = "Easy"
        ).first()

        assertThat(questions.size).isEqualTo(7)
    }
}