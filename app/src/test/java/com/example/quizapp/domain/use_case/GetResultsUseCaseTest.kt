package com.example.quizapp.domain.use_case

import com.example.quizapp.data.local.ResultEntity
import com.example.quizapp.data.repository.FakeResultRepository
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.medium_label
import com.example.quizapp.util.Constants.society_label
import com.example.quizapp.domain.model.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetResultsUseCaseTest {

    private lateinit var getResultsUseCase: GetResultsUseCase
    private lateinit var fakeResultRepository: FakeResultRepository

    @Before
    fun setUp() {
        fakeResultRepository = FakeResultRepository()
        getResultsUseCase = GetResultsUseCase(fakeResultRepository)

        val resultsToInsert = mutableListOf<ResultEntity>()

        for (i in 0..5) {
            resultsToInsert.add(
                ResultEntity(
                    score = 14,
                    category = geography_label,
                    difficulty = easy_label,
                    time = 10,
                    date = 1677691164,
                    id = i
                )
            )
        }

        for (i in 6..7) {
            resultsToInsert.add(
                ResultEntity(
                    score = 14,
                    category = geography_label,
                    difficulty = medium_label,
                    time = 10,
                    date = 1677691164,
                    id = i
                )
            )
        }

        for (i in 8..10) {
            resultsToInsert.add(
                ResultEntity(
                    score = 14,
                    category = geography_label,
                    difficulty = hard_label,
                    time = 10,
                    date = 1677691164,
                    id = i
                )
            )
        }

            for (i in 11..18) {
                resultsToInsert.add(
                    ResultEntity(
                        score = 14,
                        category = society_label,
                        difficulty = medium_label,
                        time = 10,
                        date = 1677691164,
                        id = i
                    )
                )
            }

            for (i in 19..22) {
                resultsToInsert.add(
                    ResultEntity(
                        score = 14,
                        category = history_label,
                        difficulty = easy_label,
                        time = 10,
                        date = 1677691164,
                        id = i
                    )
                )
            }


        resultsToInsert.shuffle()

        runBlocking {
            for (result in resultsToInsert) {
                fakeResultRepository.insertResult(result)
            }
        }
    }

    @Test
    fun returnListElementsAreOfTypeResult() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = easy_label
        ).first()

        for (result in results) {
            assertThat(result).isInstanceOf(Result::class.java)
        }
    }

    @Test
    fun returnedResultsAreOfCorrectCategory() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = ""
        ).first()

        for (result in results) {
            assertThat(result.category).isEqualTo(geography_label)
        }
    }

    @Test
    fun returnedResultsAreOfCorrectCategory_returnFalse() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = ""
        ).first()

        for (result in results) {
            assertThat(result.category).isNotEqualTo(society_label)
        }
    }

    @Test
    fun returnedResultsAreOfCorrectDifficulty() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = easy_label
        ).first()

        for (result in results) {
            assertThat(result.difficulty).isEqualTo(easy_label)
        }
    }

    @Test
    fun returnedResultsAreOfCorrectDifficulty_returnFlase() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = easy_label
        ).first()

        for (result in results) {
            assertThat(result.difficulty).isNotEqualTo(hard_label)
        }
    }

    @Test
    fun returnedResultsAreOfCorrectCategoryAndDifficulty() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = easy_label
        ).first()

        for (result in results) {
            assertThat(result.category).isEqualTo(geography_label)
            assertThat(result.difficulty).isEqualTo(easy_label)
        }
    }

    @Test
    fun returnedResultsAreOfCorrectCategoryAndDifficulty_returnFalse() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = easy_label
        ).first()

        for (result in results) {
            assertThat(result.category).isNotEqualTo(society_label)
            assertThat(result.difficulty).isNotEqualTo(medium_label)
        }
    }

    @Test
    fun returnedListHasAllMatchingElements_DifficultyEasy() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = easy_label
        ).first()

        assertThat(results.size).isEqualTo(6)
    }

    @Test
    fun returnedListHasAllMatchingElements_DifficultyMedium() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = medium_label
        ).first()

        assertThat(results.size).isEqualTo(2)
    }

    @Test
    fun returnedListHasAllMatchingElements_DifficultyHard() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = hard_label
        ).first()

        assertThat(results.size).isEqualTo(3)
    }

    @Test
    fun returnedListHasAllMatchingElements_DifficultyAny() = runBlocking {
        val results = getResultsUseCase(
            category = geography_label,
            query = ""
        ).first()

        assertThat(results.size).isEqualTo(11)
    }
}