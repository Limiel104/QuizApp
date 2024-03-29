package com.example.quizapp.di

import android.app.Application
import androidx.room.Room
import com.example.quizapp.data.local.QuizDatabase
import com.example.quizapp.data.remote.TriviaApi
import com.example.quizapp.data.repository.QuestionRepositoryImpl
import com.example.quizapp.data.repository.ResultRepositoryImpl
import com.example.quizapp.domain.repository.QuestionRepository
import com.example.quizapp.domain.repository.ResultRepository
import com.example.quizapp.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    fun provideTriviaApi(): TriviaApi {
        return Retrofit.Builder()
            .baseUrl(TriviaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    fun provideQuizDatabase(application: Application): QuizDatabase {
        return Room.inMemoryDatabaseBuilder(
            application,
            QuizDatabase::class.java,
        ).build()
    }

    @Provides
    fun provideQuestionRepository(api: TriviaApi, db: QuizDatabase): QuestionRepository {
        return QuestionRepositoryImpl(api,db.questionDao)
    }

    @Provides
    fun provideResultRepository(db: QuizDatabase): ResultRepository {
        return ResultRepositoryImpl(db.resultDao)
    }

    @Provides
    fun provideQuizUseCases(questionRepository: QuestionRepository, resultRepository: ResultRepository): QuizUseCases {
        return QuizUseCases(
            getQuestionsFromApiUseCase = GetQuestionsFromApiUseCase(questionRepository),
            getQuestionsFromDbUseCase = GetQuestionsFromDbUseCase(questionRepository),
            addQuestionToDbUseCase = AddQuestionToDbUseCase(questionRepository),
            addResultUseCase = AddResultUseCase(resultRepository),
            getResultsUseCase = GetResultsUseCase(resultRepository)
        )
    }
}