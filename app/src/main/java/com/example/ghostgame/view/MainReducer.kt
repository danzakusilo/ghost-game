package com.example.ghostgame.view

import com.example.ghostgame.view.model.GameEvent
import com.example.ghostgame.view.model.GameState
import com.example.ghostgame.view.model.StartButtonClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainReducer(initialState: GameState) {

    private val _state: MutableStateFlow<GameState> = MutableStateFlow(initialState)
    val state: StateFlow<GameState>
        get() = _state

    fun sendEvent(event: GameEvent) {
        reduce(_state.value, event)
    }

    private fun setState(newState: GameState){
        _state.tryEmit(newState)
    }

    private fun reduce(oldState: GameState, event: GameEvent) {
        when(event){
            is StartButtonClicked-> {
                setState(oldState.copy(gridShowing = true, startButtonShowing = false))
            }
        }
    }
}