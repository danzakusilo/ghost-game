package com.example.ghostgame.ui.game

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ghostgame.view.model.GameState

@Composable
fun ScoreText(state: GameState, modifier: Modifier, textSize: Int){
    Text(
        text = state.playerPoints.toString(),
        color = Color.White,
        fontSize = textSize.sp,
        modifier = modifier
    )
}