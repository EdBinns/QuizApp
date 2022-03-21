package com.edbinns.quizapp.di

import android.content.Context
import androidx.room.Room
import com.edbinns.quizapp.data.QuizDatabase
import com.edbinns.quizapp.data.ResultsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResultsDao(quizDatabase: QuizDatabase): ResultsDao = quizDatabase.resultsDao()


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): QuizDatabase =
        Room.databaseBuilder(
            context,
            QuizDatabase::class.java,
            "result_database")
            .fallbackToDestructiveMigration()
            .build()
}