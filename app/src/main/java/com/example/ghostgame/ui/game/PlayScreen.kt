package com.example.ghostgame.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ghostgame.MainViewModel
import com.example.ghostgame.R.string
import kotlinx.coroutines.delay

@Composable
fun PlayScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    Column() {
        if (state.needToPreviewGhosts)
            LaunchedEffect(Unit) {
                viewModel.showGhosts()
                delay(1500L)
                viewModel.hideGhosts()
            }
        ScoreText(
            state = state,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            32
        )
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(string.restart),
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