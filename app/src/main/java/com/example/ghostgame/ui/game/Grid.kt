package com.example.ghostgame.ui.game

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.ghostgame.model.Grid
import com.example.ghostgame.view.model.GameState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(onGridItemClicked: (Int, Int) -> Unit, state: GameState) {
    val grid = state.currentGrid
    LazyVerticalGrid(
        cells = GridCells.Fixed(grid.width),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(grid.height * grid.width) { index ->
            val row = index / grid.width
            val column = index % grid.width
            GhostCard(
                grid.ghostPositions[row][column], onGridItemClicked,
                grid.ghostPositions[row][column].isRevealed
            )
        }
    }
}