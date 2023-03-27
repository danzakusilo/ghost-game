package com.example.ghostgame

import androidx.lifecycle.ViewModel
import com.example.ghostgame.model.Coords
import com.example.ghostgame.view.MainReducer
import com.example.ghostgame.view.model.GameEvent
import com.example.ghostgame.view.model.GameState
import com.example.ghostgame.view.model.GridItemClicked
import com.example.ghostgame.view.model.HideGhostsEvent
import com.example.ghostgame.view.model.NextLevelClicked
import com.example.ghostgame.view.model.RestartButtonClicked
import com.example.ghostgame.view.model.ShowGhostsEvent
import com.example.ghostgame.view.model.ShowNextLevelButton
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val reducer = MainReducer(GameState.initial())

    val state: StateFlow<GameState>
        get() = reducer.state

    fun showGhosts() {
        sendEvent(ShowGhostsEvent)
    }

    fun hideGhosts() {
        sendEvent(HideGhostsEvent)
    }

    fun onGridItemClicked(x: Int, y: Int) {
        sendEvent(GridItemClicked(Coords(x, y)))
    }

    fun showNextLevelButton() {
        sendEvent(ShowNextLevelButton)
    }

    fun nextLevelClicked(){
        sendEvent(NextLevelClicked)
    }

    fun onRestartClicked() {
        sendEvent(RestartButtonClicked)
    }

    private fun sendEvent(event: GameEvent) {
        reducer.sendEvent(event)
    }
}
