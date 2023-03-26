package com.example.ghostgame.view.model

import com.example.ghostgame.model.Grid

data class GameState(
    val gridShowing: Boolean,
    val startButtonShowing: Boolean,
    val currentLevel: Level,
    val currentGrid: Grid,
    val playerPoints: Int,
    val ghostsRevealedInt: Int,
    val ghostsRemaining: Int
) {
    companion object {
        fun initial() = GameState(
            gridShowing = false,
            startButtonShowing = false,
            currentLevel = Level1,
            currentGrid = Level1.generateGrid(),
            playerPoints = 0,
            ghostsRemaining = 0,
            ghostsRevealedInt = Level1.ghostCount
        )
    }
}