package com.myapps.minesweepergame.ui.theme.gamescreen

import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameController(private val gameStateModel: GameStateManagement) {

    private val _minesWeeperGameState = MutableStateFlow<MinesWeeperGameState>(MinesWeeperGameState.Gaming)

    val minesWeeperGameState:StateFlow<MinesWeeperGameState> get() = _minesWeeperGameState

    fun onCellMarked(position: Position) {
        if (isGameActive()) {
            _minesWeeperGameState.value = gameStateModel.toggleMarkCell(position)
        }
    }

    fun onCellDig(position: Position) {
        if (isGameActive()) {
            _minesWeeperGameState.value = gameStateModel.revealCell(position)
        }
    }

    fun resetGame() {
        gameStateModel.resetGame()
        _minesWeeperGameState.value = MinesWeeperGameState.ResetGame
    }


    private fun isGameActive(): Boolean {
        return !(gameStateModel.isGameLost || gameStateModel.isGameWon)
    }
}