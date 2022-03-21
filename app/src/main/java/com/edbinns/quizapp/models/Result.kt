package com.edbinns.quizapp.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    val id:Int,
    @ColumnInfo(name= "username")
    val username: String,
    @ColumnInfo(name= "totalPoints")
    val totalPoints: Int,
)