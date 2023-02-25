package com.example.quizapp.data.mapper

import com.example.quizapp.data.local.ResultEntity
import com.example.quizapp.domain.model.Result

fun ResultEntity.toResult(): Result {
    return Result(
        score = score,
        category = category,
        difficulty = difficulty,
        time = time,
        date = date
    )
}

fun Result.toResultEntity(): ResultEntity {
    return ResultEntity(
        id = null,
        score = score,
        category = category,
        difficulty = difficulty,
        time = time,
        date = date
    )
}