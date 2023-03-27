package com.example.ghostgame.view.model

import com.example.ghostgame.model.Coords

sealed class GameEvent

object StartButtonClicked: GameEvent()

class GridItemClicked(val coords: Coords): GameEvent()

object RestartButtonClicked: GameEvent()

object ShowNextLevelButton: GameEvent()

object ShowGhostsEvent: GameEvent()

object HideGhostsEvent: GameEvent()
