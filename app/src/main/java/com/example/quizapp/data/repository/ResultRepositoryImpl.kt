package com.example.quizapp.data.repository

import com.example.quizapp.data.local.ResultDao
import com.example.quizapp.data.local.ResultEntity
import com.example.quizapp.domain.repository.ResultRepository
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val dao: ResultDao
): ResultRepository {

    override suspend fun insertResult(resultEntity: ResultEntity) {
        dao.insertResult(resultEntity)
    }

    override suspend fun getResults(
        category: String
    ): List<ResultEntity> {
        return dao.getResults(category)
    }
}