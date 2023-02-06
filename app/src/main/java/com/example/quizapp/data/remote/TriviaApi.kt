package com.example.quizapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface TriviaApi {

    @GET("questions?categories={categoryId}&limit=3&difficulty={difficultyId}")
    suspend fun getQuestions(
        @Path("categoryId") categoryId: String,
        @Path("difficultyId") difficultyId: String
    )

    companion object {
        const val BASE_URL = "https://the-trivia-api.com/api/"
    }
}