package com.example.quizapp.data.local

import com.example.quizapp.di.AppModule
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.society_label
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
class ResultDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: QuizDatabase
    private lateinit var dao: ResultDao

    @Before
    fun setUp() {
        hiltRule.inject()
        dao = db.resultDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertResult() = runBlocking {
        val result = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 1
        )

        dao.insertResult(result)
        val quizResults = dao.getResults(geography_label, easy_label)

        assertThat(quizResults.size).isEqualTo(1)
        assertThat(result).isEqualTo(quizResults[0])
    }

    @Test
    fun insertResult_manyResultsSameCategoryAndDifficulty() = runBlocking {
        val result1 = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 1
        )

        val result2 = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 2
        )

        val result3 = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 3
        )

        val result4 = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 4
        )

        val results = listOf(result1,result2,result3,result4)
        for(result in results) {
            dao.insertResult(result)
        }
        val quizResults = dao.getResults(geography_label, easy_label)

        assertThat(quizResults).hasSize(results.size)
    }

    @Test
    fun insertResult_manyResultsDifferentCategoryAndDifficulty() = runBlocking {
        val result1 = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = hard_label,
            time = 25,
            date = 1678620752,
            id = 1
        )

        val result2 = ResultEntity(
            score = 14,
            category = history_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 2
        )

        val result3 = ResultEntity(
            score = 14,
            category = geography_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 3
        )

        val result4 = ResultEntity(
            score = 14,
            category = society_label,
            difficulty = easy_label,
            time = 25,
            date = 1678620752,
            id = 4
        )

        val results = listOf(result1,result2,result3,result4)
        for(result in results) {
            dao.insertResult(result)
        }
        val quizResults = dao.getResults(geography_label, easy_label)

        assertThat(quizResults).hasSize(1)
    }
}