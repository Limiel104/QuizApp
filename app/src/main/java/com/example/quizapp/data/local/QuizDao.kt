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

    @Query("DELETE FROM questionEntity")
    suspend fun clearQuestions()
}