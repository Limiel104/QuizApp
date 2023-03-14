package com.example.quizapp.data.remote

import com.example.quizapp.util.Constants.easy_difficulty
import com.example.quizapp.util.Constants.geography_category
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_difficulty
import com.example.quizapp.util.Constants.history_label
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TriviaApiTest {

    private lateinit var api: TriviaApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TriviaApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(filename: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun sentRequestForQuestionsFromApi_receivedExpected() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        val request = server.takeRequest()
        assertThat(questions).isNotNull()
        assertThat(request.path).isEqualTo("/api/questions?limit=20&categories=geography&difficulty=easy")
    }

    @Test
    fun returnedNumberOfQuestionsIsCorrect() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        assertThat(questions.size).isEqualTo(20)
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        for (question in questions) {
            assertThat(question.category).isEqualTo(geography_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        for (question in questions) {
            assertThat(question.difficulty).isEqualTo(easy_difficulty)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectCategory_returnFalse() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        for (question in questions) {
            assertThat(question.category).isNotEqualTo(history_label)
        }
    }

    @Test
    fun returnedQuestionsAreOfCorrectDifficulty_returnFalse() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        for (question in questions) {
            assertThat(question.difficulty).isNotEqualTo(hard_difficulty)
        }
    }

    @Test
    fun returnedQuestionsHaveCorrectContent() = runBlocking {
        enqueueMockResponse("triviaResponse.json")
        val questions = api.getQuestions(geography_category, easy_difficulty)
        val question = questions[0]

        assertThat(question.question).isEqualTo("In which country is the city of Berlin?")
        assertThat(question.correctAnswer).isEqualTo("Germany")
        assertThat(question.incorrectAnswersList).isEqualTo(listOf("Croatia", "Albania", "Switzerland"))
        assertThat(question.id).isEqualTo("62602d104b176d54800e3c7c")
        assertThat(question.category).isEqualTo(geography_label)
        assertThat(question.difficulty).isEqualTo(easy_difficulty)
    }
}