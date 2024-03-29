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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline.Rounded
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ghostgame.model.GridCell
import com.example.ghostgame.view.model.GameState

const val CELL_WIDTH = 72
const val CELL_HEIGHT = 72

@Composable
fun GhostCard(
    cellData: GridCell,
    onCardClicked: (Int, Int) -> Unit,
    isRevealed: Boolean,
    state: GameState
) {
    val backgroundColor =
        if (isRevealed){
            if (state.needToPreviewGhosts)
                cellData.cardData.previewColor
            else
                cellData.cardData.revealedBackgroundColor
        } else cellData.cardData.defaultBackgroundColor
    Box(
        modifier = Modifier
            .clickable {
                if (!isRevealed) {
                    onCardClicked(cellData.coords.x, cellData.coords.y)
                }
            }
            .background(
                backgroundColor, shape = RoundedCornerShape(4.dp)
            )
            .width(CELL_WIDTH.dp)
            .height(CELL_HEIGHT.dp),
        contentAlignment = Alignment.Center

    ) {
        AnimatedVisibility(visible = isRevealed, enter = fadeIn(), exit = fadeOut()) {
            Image(
                painter = painterResource(id = cellData.cardData.iconRes),
                contentDescription = "ghost"
            )
        }
    }
}
