package com.example.ghostgame.model


data class Grid(
    val width: Int,
    val height: Int,
    val ghostPositions: List<List<GridCell>>
)

data class GridCell(
    val cardData: GameCard,
    val coords: Coords
)

data class Coords(
    val x: Int,
    val y: Int
)
