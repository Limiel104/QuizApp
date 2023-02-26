package com.example.quizapp.domain.repository

import com.example.quizapp.data.local.ResultEntity

interface ResultRepository {

    suspend fun insertResult(resultEntity: ResultEntity)

    suspend fun getResults(
        category: String,
        query: String
    ): List<ResultEntity>
}