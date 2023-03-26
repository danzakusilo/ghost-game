package com.example.ghostgame.ui.game

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.example.ghostgame.view.model.GameState
@Composable
fun MainScreen(state: GameState) {
    Box() {
        GameGrid(grid = state.currentGrid)
    }
}