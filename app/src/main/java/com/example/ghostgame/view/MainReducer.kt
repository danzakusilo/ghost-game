package com.example.ghostgame.view

import com.example.ghostgame.model.Coords
import com.example.ghostgame.model.Grid
import com.example.ghostgame.view.model.GameEvent
import com.example.ghostgame.view.model.GameState
import com.example.ghostgame.view.model.GridItemClicked
import com.example.ghostgame.view.model.RestartButtonClicked
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

    private fun setState(newState: GameState) {
        _state.tryEmit(newState)
    }

    private fun reduce(oldState: GameState, event: GameEvent) {
        when (event) {
            is StartButtonClicked -> {
                setState(oldState.copy(gridShowing = true, startButtonShowing = false))
            }
            is RestartButtonClicked -> {
                setState(GameState.initial())
            }
            is GridItemClicked -> {
                processGridItemClicked(oldState, event.coords)
            }
        }
    }

    private fun processGridItemClicked(oldState: GameState, coords: Coords) {
        val oldGrid = oldState.currentGrid
        val newGrid = oldGrid.ghostPositions.map { row ->
            row.map { cell ->
                if (cell.coords == coords)
                    cell.copy(isRevealed = true)
                else cell
            }
        }
        setState(oldState.copy(currentGrid = Grid(oldGrid.width, oldGrid.height, newGrid)))
    }
}
