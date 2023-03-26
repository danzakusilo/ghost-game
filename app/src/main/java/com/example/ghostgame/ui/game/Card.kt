package com.example.ghostgame.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ghostgame.model.GameCard
import com.example.ghostgame.ui.theme.GameCellDefault

const val CELL_WIDTH = 72
const val CELL_HEIGHT = 72

@Composable
fun GhostCard(cardData: GameCard) {
    var isAnswerRevealed by remember { mutableStateOf(false) }
    val backgroundColor =
        if (isAnswerRevealed) cardData.revealedBackgroundColor else cardData.defaultBackgroundColor
    Box(
        modifier = Modifier
            .clickable {
                isAnswerRevealed = !isAnswerRevealed
            }
            .background(
                backgroundColor, shape = RectangleShape
            )
            .width(CELL_WIDTH.dp)
            .height(CELL_HEIGHT.dp),
        contentAlignment = Alignment.Center

    ) {
        AnimatedVisibility(visible = isAnswerRevealed, enter = fadeIn(), exit = fadeOut()) {
            Image(painter = painterResource(id = cardData.iconRes), contentDescription = "ghost")
        }
    }

}
