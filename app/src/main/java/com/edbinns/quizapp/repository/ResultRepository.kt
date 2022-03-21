package com.edbinns.quizapp.repository

import com.edbinns.quizapp.data.ResultsDao
import com.edbinns.quizapp.models.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(private val resultsDao: ResultsDao) {

    fun getResults(): Flow<List<Result>> = resultsDao.getResults()

    suspend fun addResult(result: Result) = resultsDao.addResult(result)

    suspend fun updateResult(result: Result) = resultsDao.updateResult(result)

    suspend fun deleteResult(result: Result) = resultsDao.deleteResult(result)

}