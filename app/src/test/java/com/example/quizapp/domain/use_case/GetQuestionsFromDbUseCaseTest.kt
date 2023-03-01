package com.example.quizapp.domain.use_case

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.data.repository.FakeQuestionRepository
import com.example.quizapp.domain.model.Question
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.knowledge_label
import com.example.quizapp.util.Constants.medium_label
import com.example.quizapp.util.Constants.society_label
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuestionsFromDbUseCaseTest {

    private lateinit var getQuestionsFromDbUseCase: GetQuestionsFromDbUseCase
    private lateinit var fakeQuizRepository: FakeQuestionRepository

    @Before
    fun setUp() {
        fakeQuizRepository = FakeQuestionRepository()
        getQuestionsFromDbUseCase = GetQuestionsFromDbUseCase(fakeQuizRepository)

        val questionsToInsert = mutableListOf<QuestionEntity>()

        for(i in 0..QUESTIONS_NUMBER) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = geography_label,
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = easy_label,
                )
            )
        }

        for(i in QUESTIONS_NUMBER+1..2*QUESTIONS_NUMBER) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = society_label,
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = easy_label,
                )
            )
        }

        for(i in 2*QUESTIONS_NUMBER+1..3*QUESTIONS_NUMBER) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = society_label,
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = hard_label,
                )
            )
        }

        for(i in 3*QUESTIONS_NUMBER+1..4*QUESTIONS_NUMBER) {
            questionsToInsert.add(
                QuestionEntity(
                    id = i.toString(),
                    category = geography_label,
                    correctAnswer = "Correct",
                    incorrectAnswer1 = "Incorrect1",
                    incorrectAnswer2 = "Incorrect2",
                    incorrectAnswer3 = "Incorrect3",
                    question = "Question $i",
                    difficulty = medium_label,
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
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question).isInstanceOf(Question::class.java)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.category).isEqualTo(geography_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory_returnFalse() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.category).isNotEqualTo(knowledge_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isEqualTo(easy_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty_returnFalse() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        for(question in questions) {
            assertThat(question.difficulty).isNotEqualTo(hard_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategoryAndDifficulty() = runBlocking {
        val questions = getQuestionsFromDbUseCase(
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
        val questions = getQuestionsFromDbUseCase(
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
        val questions = getQuestionsFromDbUseCase(
            category = geography_label,
            difficulty = easy_label
        ).first()

        assertThat(questions.size).isEqualTo(QUESTIONS_NUMBER)
    }
}