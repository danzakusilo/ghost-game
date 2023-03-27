package com.example.ghostgame.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ghostgame.MainViewModel
import com.example.ghostgame.R
import com.example.ghostgame.R.drawable
import com.example.ghostgame.R.string
import com.example.ghostgame.view.model.GameState
import kotlinx.coroutines.delay

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
        SplashSequence(
            viewModel = viewModel,
            state = state,
            modifier = Modifier.align(Alignment.Center)
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


@Composable
fun SplashSequence(viewModel: MainViewModel, state: GameState, modifier: Modifier) {
    Box(modifier = modifier) {
        LaunchedEffect(Unit) {
            viewModel.showSplashImage()
            delay(2000)
            viewModel.showStartButton()
        }
        AnimatedVisibility(
            visible = state.showSplashImage, enter = fadeIn(), exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = drawable.ic_game_icon),
                contentDescription = "splash image",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        AnimatedVisibility(
            visible = state.startButtonShowing,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.Center)
        ) {
            GhostButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White, shape = RoundedCornerShape(24))
                    .clickable { viewModel.onStartButtonClicked() }
                    .padding(vertical = 16.dp, horizontal = 48.dp),
                string.start
            )
        }
    }
}