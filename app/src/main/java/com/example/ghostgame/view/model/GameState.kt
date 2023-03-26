package com.example.ghostgame.view.model

import com.example.ghostgame.model.Grid

data class GameState(
    val currentLevel: Level,
    val currentGrid: Grid,
    val playerPoints: Int,
    val ghostsRevealedInt: Int,
    val ghostsRemaining: Int
) {
    companion object {
        fun initial() = GameState(
            currentLevel = Level1,
            currentGrid = Level1.generateGrid(),
            playerPoints = 0,
            ghostsRemaining = 0,
            ghostsRevealedInt = Level1.ghostCount
        )
    }
}