package com.example.quizapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuestionEntity::class],
    version = 1
)
abstract class QuizDatabase: RoomDatabase() {

    abstract val dao: QuizDao
}