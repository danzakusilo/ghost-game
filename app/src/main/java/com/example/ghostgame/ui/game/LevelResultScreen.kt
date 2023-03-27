package com.example.ghostgame.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ghostgame.MainViewModel
import com.example.ghostgame.R
import com.example.ghostgame.view.model.GameState

@Composable
fun LevelResultScreen(state: GameState, viewModel: MainViewModel, modifier: Modifier) {
    Column(modifier = modifier) {
        ScoreText(
            state = state,
            modifier = Modifier
                .padding(top = 64.dp)
                .align(Alignment.CenterHorizontally), 48
        )
        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            GhostButton(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White, shape = RoundedCornerShape(24))
                    .clickable { viewModel.nextLevelClicked() }
                    .padding(vertical = 14.dp, horizontal = 32.dp),
                R.string.next_level
            )
        }
    }
}