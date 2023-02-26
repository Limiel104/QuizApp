package com.example.quizapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(
        resultEntity: ResultEntity
    )

    @Query(
        """
            SELECT *
            FROM resultEntity
            WHERE category = :category
            AND difficulty LIKE '%' || :query || '%'
            ORDER BY score DESC
            LIMIT 20
        """
    )
    suspend fun getResults(
        category: String,
        query: String
    ): List<ResultEntity>
}