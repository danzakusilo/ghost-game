package com.example.ghostgame.ui.game

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.ghostgame.ui.theme.BluePrimary

@Composable
fun GhostButton(modifier: Modifier, textRes: Int) {
    Text(
        text = stringResource(id = textRes),
        color = BluePrimary,
        modifier = modifier,
        fontSize = 16.sp
    )
}