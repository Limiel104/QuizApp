package com.example.quizapp.data.remote

import com.example.quizapp.domain.model.Question
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApi {

    @GET("api/questions?limit=3")
    suspend fun getQuestions(
        @Query("categories") categoryId: String,
        @Query("difficulty") difficultyId: String
    ): List<Question>

    companion object {
        const val BASE_URL = "https://the-trivia-api.com/"
    }
}