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
fun GameGrid(grid: Grid, onGridItemClicked: (Int, Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(grid.width),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(grid.height * grid.width) { index ->
            val row = index / grid.height
            val column = index % grid.width
            GhostCard(grid.ghostPositions[row][column], onGridItemClicked)
        }
    }
}