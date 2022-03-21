package com.edbinns.quizapp.data

import androidx.room.*
import com.edbinns.quizapp.models.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultsDao {

    @Query("SELECT * FROM result ORDER BY totalPoints DESC LIMIT 10")
    fun getResults(): Flow<List<Result>>

    @Query("SELECT * FROM result WHERE id=:id")
    suspend fun getResultById(id:Int): Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addResult(result: Result)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateResult(result: Result)

    @Query("DELETE FROM result")
    suspend fun deleteAllResult()

    @Delete
    suspend fun deleteResult(result: Result)
}