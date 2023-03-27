package com.example.ghostgame.view.model

import androidx.compose.runtime.Composer.Companion.Empty
import com.example.ghostgame.model.CardCorrect
import com.example.ghostgame.model.CardWrong
import com.example.ghostgame.model.Coords
import com.example.ghostgame.model.Grid
import com.example.ghostgame.model.GridCell
import com.example.ghostgame.ui.util.getRandomGhostImage
import kotlin.random.Random

sealed class Level(
    val gridWidth: Int,
    val gridHeight: Int,
    val ghostCount: Int,
    val pointsPerGuess: Int = 5,
    val nextLevel: Level?
) {
    fun generateGrid(): Grid {
        val grid = MutableList(gridHeight) { MutableList(gridWidth) { Empty } }

        val random = Random(System.currentTimeMillis())
        repeat(ghostCount) {
            var x = random.nextInt(gridWidth)
            var y = random.nextInt(gridHeight)
            while (grid[y][x] != Empty) {
                x = random.nextInt(gridWidth)
                y = random.nextInt(gridHeight)
            }
            grid[y][x] = GridCell(
                CardCorrect(getRandomGhostImage()),
                Coords(x, y),
                false
            )
        }

        for (y in 0 until gridHeight) {
            for (x in 0 until gridWidth) {
                if (grid[y][x] == Empty) {
                    grid[y][x] = GridCell(
                        CardWrong,
                        Coords(x, y),
                        false
                    )
                }
            }
        }

        return Grid(gridWidth, gridHeight, grid as List<List<GridCell>>)
    }
}

object Level1 : Level(
    4, 4, 4, nextLevel = Level2
)

object Level2 : Level(
    4, 5, 5, nextLevel = Level3
)

object Level3 : Level(
    4, 6, 6, nextLevel = Level4
)

object Level4 : Level(
    5, 6, 7, nextLevel = Level5
)

object Level5 : Level(
    5, 7, 8, nextLevel = null
)
