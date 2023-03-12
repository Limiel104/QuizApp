package com.example.quizapp.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.society_category
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuestionDaoTest {

    private lateinit var db: QuizDatabase
    private lateinit var dao: QuestionDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuizDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = db.questionDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertQuestions_onlyOneQuestion() = runBlocking {
        val question = QuestionEntity(
            id = "1",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question"
        )

        val questionList = listOf(question)
        dao.insertQuestions(questionList)
        val quizQuestions = dao.getQuestions(geography_label, hard_label)

        assertThat(quizQuestions).isEqualTo(questionList)
    }

    @Test
    fun insertQuestions_manyQuestionsSameCategoryAndDifficulty() = runBlocking {
        val question1 = QuestionEntity(
            id = "1",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question1"
        )

        val question2 = QuestionEntity(
            id = "2",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question2"
        )

        val question3 = QuestionEntity(
            id = "3",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question3"
        )

        val question4 = QuestionEntity(
            id = "4",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question4"
        )

        val questionList = listOf(question1,question2,question3,question4)
        dao.insertQuestions(questionList)
        val quizQuestions = dao.getQuestions(geography_label, hard_label)

        assertThat(quizQuestions).hasSize(questionList.size)
    }

    @Test
    fun insertQuestions_manyQuestionsDifferentCategoryAndDifficulty() = runBlocking {
        val question1 = QuestionEntity(
            id = "1",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question1"
        )

        val question2 = QuestionEntity(
            id = "2",
            category = history_label,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question2"
        )

        val question3 = QuestionEntity(
            id = "3",
            category = geography_label,
            correctAnswer = "Correct",
            difficulty = easy_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question3"
        )

        val question4 = QuestionEntity(
            id = "4",
            category = society_category,
            correctAnswer = "Correct",
            difficulty = hard_label,
            incorrectAnswer1 = "Incorrect1",
            incorrectAnswer2 = "Incorrect2",
            incorrectAnswer3 = "Incorrect3",
            question = "Question4"
        )

        val questionList = listOf(question1,question2,question3,question4)
        dao.insertQuestions(questionList)
        val quizQuestions = dao.getQuestions(geography_label, hard_label)

        assertThat(quizQuestions).hasSize(1)
    }
}