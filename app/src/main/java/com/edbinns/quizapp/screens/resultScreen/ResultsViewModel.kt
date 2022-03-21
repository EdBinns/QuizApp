package com.edbinns.quizapp.screens.resultScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edbinns.quizapp.models.Result
import com.edbinns.quizapp.repository.ResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(private val resultRepository: ResultRepository) : ViewModel() {

    private val _resultList = MutableStateFlow<List<Result>>(emptyList())
    val resultList = _resultList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            resultRepository.getResults().distinctUntilChanged().collect { list ->
                if(list.isNullOrEmpty())
                    Log.d("TAG", "FavoriteViewModel: EmptyList ")
                else {
                    _resultList.value = list
                    list.forEach {
                        Log.d("TAG", "FavoriteViewModel: ${it}")
                    }
                    Log.d("TAG", "FavoriteViewModel: ${_resultList.value}")
                }
            }
        }
    }
}