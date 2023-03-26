package com.example.ghostgame.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.ghostgame.MainViewModel
import com.example.ghostgame.R

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    Box() {
        Image(
            painterResource(id = R.drawable.ic_ghosts_bg),
            "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        GameGrid(grid = state.currentGrid, viewModel::onGridItemClicked)
    }
}