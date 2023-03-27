package com.example.ghostgame.view.model

import com.example.ghostgame.model.Grid

data class GameState(
    val needToPreviewGhosts: Boolean,
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
        fun initial() = getNextClearLevelState(Level1, 0)

        fun getNextClearLevelState(level: Level, points: Int) = GameState(
            needToPreviewGhosts = true,
            gridShowing = false,
            startButtonShowing = false,
            currentLevel = level,
            currentGrid = level.generateGrid(),
            playerPoints = points,
            ghostsRemaining = level.ghostCount,
            clicksRemaining = level.ghostCount,
            showGameWon = false,
            showGameLost = false,
            showGrid = true,
            showNextLevelScreen = false
        )
    }
}