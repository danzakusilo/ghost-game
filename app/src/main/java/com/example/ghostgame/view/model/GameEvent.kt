package com.example.ghostgame.view.model

import com.example.ghostgame.model.Coords

sealed class GameEvent

object StartButtonClicked: GameEvent()

class GridItemClicked(val coords: Coords): GameEvent()


