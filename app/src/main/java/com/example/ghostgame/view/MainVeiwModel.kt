package com.example.ghostgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghostgame.model.Coords
import com.example.ghostgame.view.MainReducer
import com.example.ghostgame.view.model.GameEvent
import com.example.ghostgame.view.model.GameState
import com.example.ghostgame.view.model.GridItemClicked
import com.example.ghostgame.view.model.RestartButtonClicked
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

    fun onGridItemClicked(x: Int, y: Int) {
        sendEvent(GridItemClicked(Coords(x, y)))
    }

    fun onRestartClicked(){
        sendEvent(RestartButtonClicked)
    }

    private fun sendEvent(event: GameEvent) {
        reducer.sendEvent(event)
    }
}
