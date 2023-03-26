package com.example.ghostgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghostgame.view.MainReducer
import com.example.ghostgame.view.model.GameState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val reducer = MainReducer(GameState.initial())

    val state: StateFlow<GameState>
        get() = reducer.state

    init {
        viewModelScope.launch {

        }
    }
}
