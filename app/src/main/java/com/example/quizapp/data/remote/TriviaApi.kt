package com.example.quizapp.data.remote

import com.example.quizapp.domain.model.Question
import retrofit2.http.GET
import retrofit2.http.Path

interface TriviaApi {

    @GET("questions?categories={categoryId}&limit=3&difficulty={difficultyId}")
    suspend fun getQuestions(
        @Path("categoryId") categoryId: String,
        @Path("difficultyId") difficultyId: String
    ): List<Question>

    companion object {
        const val BASE_URL = "https://the-trivia-api.com/api/"
    }
}