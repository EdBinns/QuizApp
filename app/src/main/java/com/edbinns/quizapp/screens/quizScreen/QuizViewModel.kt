package com.edbinns.quizapp.screens.quizScreen

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edbinns.quizapp.models.Question
import com.edbinns.quizapp.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
) : ViewModel() {

    val actualQuestion: MutableState<Question?> = mutableStateOf(null)
    private val questions = mutableListOf<Question>()

    private var counter = 30
    val time: MutableState<String> = mutableStateOf("")

    private val timer = object : CountDownTimer(30000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            time.value = counter.toString()
            counter--
        }

        override fun onFinish() {
            time.value =  counter.toString()
        }
    }

    init {
        getQuestion()
        startTimeCounter()
    }

    fun getQuestion() {

        viewModelScope.launch {
            if (questions.size < 10) {
                val newQuestion = getRandomQuestion()
                questions.add(newQuestion)
                actualQuestion.value = newQuestion
                Log.d("GET", "getQuestion: ${actualQuestion.value} ${questions.size}")
            }
        }

    }


    private suspend fun getRandomQuestion(): Question {
        var newQuestion = quizRepository.getRandomQuestion()

        while ((questions.contains(newQuestion))) {
            newQuestion = quizRepository.getRandomQuestion()
        }

        return newQuestion
    }


    fun startTimeCounter() {
        timer.start()
    }
    fun endTimeCounter() {
        counter = 30
        timer.cancel()
    }
}