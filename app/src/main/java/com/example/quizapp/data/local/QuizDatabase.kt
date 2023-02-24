package com.example.quizapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuestionEntity::class, ResultEntity::class],
    version = 1
)
abstract class QuizDatabase: RoomDatabase() {

    abstract val quizDao: QuizDao
    abstract val resultDao: ResultDao

    companion object {
        const val DATABASE_NAME = "quizdb.db"
    }
}