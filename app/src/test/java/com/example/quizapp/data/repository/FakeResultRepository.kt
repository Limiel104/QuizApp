package com.example.quizapp.data.repository

import com.example.quizapp.data.local.ResultEntity
import com.example.quizapp.domain.repository.ResultRepository
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER

class FakeResultRepository: ResultRepository {

    private val resultEntityLis = mutableListOf<ResultEntity>()

    override suspend fun insertResult(resultEntity: ResultEntity) {
        resultEntityLis.add(resultEntity)
    }

    override suspend fun getResults(
        category: String,
        query: String
    ): List<ResultEntity> {
        val resultsFromDb = mutableListOf<ResultEntity>()

        for(result in resultEntityLis) {
            if(resultsFromDb.size < QUESTIONS_NUMBER) {
                if(result.category == category && (result.difficulty == query || query == "")) {
                    resultsFromDb.add(result)
                }
            }
        }

        return resultsFromDb
    }
}