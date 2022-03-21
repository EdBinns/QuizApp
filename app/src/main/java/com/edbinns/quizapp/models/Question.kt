package com.edbinns.quizapp.models

data class Question(
    val question: String,
    val answers : List<String>,
    val imageUrl : String,
    val correct : String
)