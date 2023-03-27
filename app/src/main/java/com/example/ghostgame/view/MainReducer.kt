package com.example.ghostgame.view

import com.example.ghostgame.model.CardCorrect
import com.example.ghostgame.model.Coords
import com.example.ghostgame.model.Grid
import com.example.ghostgame.model.GridCell
import com.example.ghostgame.ui.theme.GameCellCorrect
import com.example.ghostgame.view.model.GameEvent
import com.example.ghostgame.view.model.GameState
import com.example.ghostgame.view.model.GridItemClicked
import com.example.ghostgame.view.model.HideGhostsEvent
import com.example.ghostgame.view.model.NextLevelClicked
import com.example.ghostgame.view.model.RestartButtonClicked
import com.example.ghostgame.view.model.ShowGhostsEvent
import com.example.ghostgame.view.model.ShowNextLevelButton
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
            is ShowNextLevelButton -> {
                processLevelFinished(oldState)
            }
            is ShowGhostsEvent -> {
                processShowGhostsEvent(oldState)
            }
            is HideGhostsEvent -> {
                processHideGhostsEvent(oldState)
            }
            is NextLevelClicked -> {
                processNextLevelClicked(oldState)
            }
        }
    }

    private fun processNextLevelClicked(oldState: GameState) {
        goToNextLevel(oldState)
    }

    private fun goToNextLevel(oldState: GameState) {
        val oldLevel = oldState.currentLevel
        oldLevel.nextLevel?.let { nextLevel ->
            setState(GameState.getNextClearLevelState(nextLevel, oldState.playerPoints))
        }
    }

    private fun processShowGhostsEvent(oldState: GameState) {
        toggleAllGhostsVisibility(oldState, true)
    }

    private fun processHideGhostsEvent(oldState: GameState) {
        toggleAllGhostsVisibility(oldState.copy(needToPreviewGhosts = false), false)
    }

    private fun toggleAllGhostsVisibility(oldState: GameState, isRevealed: Boolean) {
        val oldGrid = oldState.currentGrid.ghostPositions
        val newGrid = oldGrid.map { row ->
            row.map { cell ->
                if (cell.cardData is CardCorrect)
                    cell.copy(isRevealed = isRevealed)
                else cell
            }
        }
        setState(
            oldState.copy(
                currentGrid = Grid(
                    oldState.currentGrid.width, oldState.currentGrid.height, newGrid
                )
            )
        )
    }

    private fun processLevelFinished(oldState: GameState) {
        setState(
            oldState.copy(
                showGameLost = false,
                showGameWon = false,
                showGrid = false,
                showNextLevelScreen = true
            )
        )
    }

    private fun processGridItemClicked(oldState: GameState, coords: Coords) {
        val newGrid = updateGridItemVisibility(oldState.currentGrid.ghostPositions, coords)
        val newPlayerPoints = updatePlayerPoints(oldState, coords)
        val ghostsRemaining = updateGhostsRemaining(oldState, coords)
        val currentClicksRemaining = oldState.clicksRemaining
        val newClicksRemaining = currentClicksRemaining.minus(1)
        setState(
            oldState.copy(
                currentGrid = Grid(
                    oldState.currentGrid.width,
                    oldState.currentGrid.height,
                    newGrid
                ),
                playerPoints = newPlayerPoints,
                ghostsRemaining = updateGhostsRemaining(oldState, coords),
                clicksRemaining = currentClicksRemaining.minus(1),
                showGameWon = newClicksRemaining == 0 && ghostsRemaining == 0,
                showGameLost = newClicksRemaining == 0 && ghostsRemaining > 0
            )
        )
    }

    private fun updateGhostsRemaining(oldState: GameState, coords: Coords): Int {
        if (oldState.currentGrid.ghostPositions[coords.y][coords.x].cardData is CardCorrect) {
            val currentGhostsRemaining = oldState.ghostsRemaining
            return currentGhostsRemaining.minus(1)
        }
        return oldState.ghostsRemaining
    }

    private fun updatePlayerPoints(oldState: GameState, coords: Coords): Int {
        if (oldState.currentGrid.ghostPositions[coords.y][coords.x].cardData is CardCorrect) {
            val currentPlayerPoints = oldState.playerPoints
            val pointsPerGuess = oldState.currentLevel.pointsPerGuess
            return currentPlayerPoints.plus(pointsPerGuess)
        }
        return oldState.playerPoints
    }

    private fun updateGridItemVisibility(
        oldGrid: List<List<GridCell>>,
        coords: Coords
    ): List<List<GridCell>> {
        return oldGrid.map { colon ->
            colon.map { cell ->
                if (cell.coords.x == coords.x && cell.coords.y == coords.y)
                    cell.copy(isRevealed = true)
                else cell
            }
        }
    }
}
