package com.example.quizapp.domain.use_case

import android.util.Log
import com.example.quizapp.data.mapper.toResultEntity
import com.example.quizapp.domain.repository.ResultRepository
import com.example.quizapp.domain.model.Result

class AddResultUseCase(
    private val resultRepository: ResultRepository
) {
    suspend operator fun invoke(result: Result) {
        Log.i("TAG LOCAL R SAVE", result.toString())
        resultRepository.insertResult(result.toResultEntity())
    }
}