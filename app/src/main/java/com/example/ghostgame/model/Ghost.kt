package com.example.ghostgame.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.ghostgame.R
import com.example.ghostgame.ui.theme.GameCellCorrect
import com.example.ghostgame.ui.theme.GameCellDefault
import com.example.ghostgame.ui.theme.GameCellGhost
import com.example.ghostgame.ui.theme.GameCellWrong

sealed class GameCard(
    @DrawableRes val iconRes: Int,
    val defaultBackgroundColor: Color,
    val revealedBackgroundColor: Color,
    val previewColor: Color
)

object CardWrong :
    GameCard(R.drawable.ic_ghost_wrong, GameCellDefault, GameCellWrong, GameCellWrong)

class CardCorrect(@DrawableRes ghostIconRes: Int) :
    GameCard(ghostIconRes, GameCellDefault, GameCellCorrect, GameCellGhost) {
}
