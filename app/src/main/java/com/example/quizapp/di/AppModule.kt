package com.example.quizapp.di

import android.app.Application
import androidx.room.Room
import com.example.quizapp.data.local.QuizDatabase
import com.example.quizapp.data.remote.TriviaApi
import com.example.quizapp.data.repository.QuizRepositoryImpl
import com.example.quizapp.domain.repository.QuizRepository
import com.example.quizapp.domain.use_case.GetQuestionsFromApiUseCase
import com.example.quizapp.domain.use_case.GetQuestionsFromDbUseCase
import com.example.quizapp.domain.use_case.QuizUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTriviaApi(): TriviaApi {
        return Retrofit.Builder()
            .baseUrl(TriviaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideQuizDatabase(application: Application): QuizDatabase {
        return Room.databaseBuilder(
            application,
            QuizDatabase::class.java,
            QuizDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(api: TriviaApi, db: QuizDatabase): QuizRepository {
        return QuizRepositoryImpl(api,db.dao)
    }

    @Provides
    @Singleton
    fun provideQuizUseCases(repository: QuizRepository): QuizUseCases {
        return QuizUseCases(
            getQuestionsFromApiUseCase = GetQuestionsFromApiUseCase(repository),
            getQuestionsFromDbUseCase = GetQuestionsFromDbUseCase(repository)
        )
    }
}