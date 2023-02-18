package com.example.quizapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(
        questionEntities: List<QuestionEntity>
    )

    @Query(
        """
            SELECT * 
            FROM questionEntity
            WHERE category = :category
            AND difficulty LIKE :difficulty
            ORDER BY random()
            LIMIT 7
        """
    )
    suspend fun getQuestions(
        category: String,
        difficulty: String
    ): List<QuestionEntity>
}