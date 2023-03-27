package com.example.ghostgame.view.model

import com.example.ghostgame.model.Grid

data class GameState(
    val isInInitialState: Boolean,
    val gridShowing: Boolean,
    val startButtonShowing: Boolean,
    val currentLevel: Level,
    val currentGrid: Grid,
    val playerPoints: Int,
    val ghostsRemaining: Int,
    val clicksRemaining: Int,
    val showGameWon: Boolean,
    val showGameLost: Boolean,
    val showGrid: Boolean,
    val showNextLevelScreen: Boolean
) {
    companion object {
        fun initial() = GameState(
            isInInitialState = false,
            gridShowing = false,
            startButtonShowing = false,
            currentLevel = Level1,
            currentGrid = Level1.generateGrid(),
            playerPoints = 0,
            ghostsRemaining = Level1.ghostCount,
            clicksRemaining = Level1.ghostCount,
            showGameWon = false,
            showGameLost = false,
            showGrid = true,
            showNextLevelScreen = false
        )
    }
}