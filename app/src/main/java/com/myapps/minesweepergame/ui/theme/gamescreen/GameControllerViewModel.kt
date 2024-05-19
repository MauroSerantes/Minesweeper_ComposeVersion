package com.myapps.minesweepergame.ui.theme.gamescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.minesweepergame.domain.MinesweeperBoard
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.CellData
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class GameControllerViewModel(private val gameController: GameController) : ViewModel() {

    val gameState = gameController.minesWeeperGameState
        .stateIn(viewModelScope, SharingStarted.Lazily,MinesWeeperGameState.Gaming)

    fun onEvent(event: MatrixClickedEvents) {
        when (event) {
            is MatrixClickedEvents.OnCellDig -> {
               gameController.onCellDig(event.position)
            }

            is MatrixClickedEvents.OnCellMarked -> {
                gameController.onCellMarked(event.position)
            }

            is MatrixClickedEvents.OnResetGame -> {
                gameController.resetGame()
            }
        }
    }
}