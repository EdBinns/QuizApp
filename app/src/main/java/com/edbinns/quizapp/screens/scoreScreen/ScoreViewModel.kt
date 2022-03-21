package com.edbinns.quizapp.screens.scoreScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edbinns.quizapp.models.Result
import com.edbinns.quizapp.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScoreViewModel @Inject constructor(private val resultRepository: ResultRepository): ViewModel() {

    fun addResult(result: Result){
        viewModelScope.launch {
            resultRepository.addResult(result)
        }
    }
}