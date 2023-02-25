package com.example.quizapp.domain.use_case

import android.util.Log
import com.example.quizapp.data.mapper.toResult
import com.example.quizapp.domain.repository.ResultRepository
import javax.inject.Inject
import com.example.quizapp.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetResultsUseCase @Inject constructor(
    private val resultRepository: ResultRepository
) {
    operator fun invoke(category: String): Flow<List<Result>> = flow {
        val results = resultRepository.getResults(category)
        Log.i("TAG LOCAL R",results.toString())
        if(results.isNotEmpty()) {
            emit(results.map { it.toResult() })
        }
    }
}