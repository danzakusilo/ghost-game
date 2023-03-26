package com.example.ghostgame.ui.game

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.ghostgame.model.Grid

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(grid: Grid) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(grid.width),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(grid.height * grid.width) { index ->
            val row = index / grid.height
            val column = index % grid.width
            GhostCard(cardData = grid.ghostPositions[row][column].cardData)
        }
    }
}