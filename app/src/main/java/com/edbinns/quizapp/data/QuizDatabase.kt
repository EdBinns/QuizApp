package com.edbinns.quizapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edbinns.quizapp.models.Result

@Database(entities = [Result::class], version = 2, exportSchema = false )
abstract class QuizDatabase : RoomDatabase(){

    abstract fun resultsDao() : ResultsDao
}