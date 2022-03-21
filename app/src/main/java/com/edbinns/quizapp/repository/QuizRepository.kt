package com.edbinns.quizapp.repository

import com.edbinns.quizapp.data.QuizProvider
import com.edbinns.quizapp.models.Question
import javax.inject.Inject


class QuizRepository @Inject constructor(private val quizProvider: QuizProvider){


    suspend fun getRandomQuestion(): Question {
        val questionList = quizProvider.createQuiz()
        val number = (questionList.indices).random()
        return questionList[number]
    }
}