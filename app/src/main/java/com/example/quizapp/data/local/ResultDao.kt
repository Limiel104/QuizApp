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
            LIMIT 20
        """
    )
    suspend fun getResults(): List<ResultEntity>
}