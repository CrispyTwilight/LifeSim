// Auth: John O'Neal
// Date: 05/09/2024
// Desc: A view model for the game screen

package com.example.lifesimapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifesimapp.classes.Option
import com.example.lifesimapp.classes.Question
import com.example.lifesimapp.data.getQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    val selectedOption = MutableStateFlow<Option?>(null)
    val questions = MutableStateFlow<List<Question>>(emptyList())

    fun selectOption(option: Option) {
        selectedOption.value = option
        Log.d("GameViewModel", "Selected option: $option")
    }

    fun loadQuestions(context: Context) {
        viewModelScope.launch {
            Log.d("GameViewModel", "Loading questions")
            val loadedQuestions = getQuestions(context)
            Log.d(
                "GameViewModel",
                "Loaded questions: $loadedQuestions"
            )
            questions.value = loadedQuestions
        }
    }
}