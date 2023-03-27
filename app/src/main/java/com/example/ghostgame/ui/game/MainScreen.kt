package com.example.ghostgame.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.ghostgame.MainViewModel
import com.example.ghostgame.R
import kotlinx.coroutines.delay

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    if (state.needToPreviewGhosts)
        LaunchedEffect(Unit) {
            viewModel.showGhosts()
            delay(1500L)
            viewModel.hideGhosts()
        }
    Box() {
        Image(
            painterResource(id = R.drawable.ic_ghosts_bg),
            "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        AnimatedVisibility(visible = state.showGrid, enter = fadeIn(), exit = fadeOut()) {
            PlayScreen(viewModel = viewModel)
        }
        val image =
            if (state.showGameWon) R.drawable.ic_game_won
            else if (state.showGameLost) R.drawable.ic_game_lost
            else null
        AnimatedVisibility(
            visible = state.showGameWon || state.showGameLost,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            image?.let {
                Image(
                    painter = painterResource(id = image), contentDescription = "level finish state"
                )
                LaunchedEffect(Unit) {
                    delay(1000L)
                    viewModel.showNextLevelButton()
                }
            }
        }
        AnimatedVisibility(
            visible = state.showNextLevelScreen,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            LevelResultScreen(
                state = state,
                viewModel = viewModel,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}