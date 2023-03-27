package com.example.ghostgame.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ghostgame.MainViewModel
import com.example.ghostgame.R
import kotlinx.coroutines.delay

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.showGhosts()
        delay(2000L)
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
            Column() {
                Text(
                    text = state.playerPoints.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    fontSize = 24.sp
                )
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.restart),
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .background(Color.LightGray, shape = CircleShape)
                            .padding(8.dp)
                            .clickable {
                                viewModel.onRestartClicked()
                            }
                    )
                }
                GameGrid(viewModel::onGridItemClicked, state)
            }
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
    }
}