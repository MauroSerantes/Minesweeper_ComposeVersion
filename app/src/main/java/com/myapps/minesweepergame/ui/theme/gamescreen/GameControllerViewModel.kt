package com.myapps.minesweepergame.ui.theme.gamescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.myapps.minesweepergame.domain.MinesweeperBoard
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.CellData
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameControllerViewModel(private val gameBoard: MinesweeperBoard) : ViewModel() {

    private val _state = MutableStateFlow<MinesWeeperGameState>(MinesWeeperGameState.Gaming)

    private val _flagCounter = mutableStateOf(gameBoard.amountOfMines)

    private val gameLose = mutableStateOf(false)

    private val gameWin = mutableStateOf(false)

    val state: StateFlow<MinesWeeperGameState> get() = _state


    private fun onCellMarked(position: Position) {
        if (gameLose.value || gameWin.value) return

        val cell = gameBoard.getMineSweeperCell(position.positionI, position.positionJ)

        if (cell.isRevealed()) return

        if (cell.isMarked()) {
            _flagCounter.value += 1
            cell.setMarked()
            _state.value = MinesWeeperGameState.UnmarkedCell(position, _flagCounter.value)
        } else {
            if (_flagCounter.value > 0) {
                _flagCounter.value -= 1
                cell.setMarked()
                _state.value = MinesWeeperGameState.MarkCell(position, _flagCounter.value)
            }
        }
    }

    private fun onCellDig(position: Position) {
        if (gameLose.value || gameWin.value) return

        val cell = gameBoard.getMineSweeperCell(position.positionI, position.positionJ)

        if (cell.isRevealed() || cell.isMarked()) return

        cell.setRevealed()

        if (cell.isMine()) {
            _state.value = MinesWeeperGameState.GameLose(locationsOfAllMines())
            gameLose.value = true
        } else {
            val list = ArrayList<CellData>()
            addRevealedCellsToListOnPress(position, list)
            checkWinCondition()
            _state.value = MinesWeeperGameState.RevealNumberCells(list)
        }

    }


    fun onEvent(event: MatrixClickedEvents) {
        when (event) {
            is MatrixClickedEvents.OnCellDig -> {
                onCellDig(event.position)
            }

            is MatrixClickedEvents.OnCellMarked -> {
                onCellMarked(event.position)
            }

            is MatrixClickedEvents.OnResetGame -> {
                resetGame()
            }
        }
    }

    private fun resetGame() {
        gameBoard.resetBoard()
        gameWin.value = false
        gameLose.value = false
        _flagCounter.value = gameBoard.amountOfMines
        _state.value = MinesWeeperGameState.ResetGame
    }


    private fun locationsOfAllMines(): List<Position> {
        val list = ArrayList<Position>()

        for (i in 0 until gameBoard.getRows()) {

            for (j in 0 until gameBoard.getColumns()) {

                if (gameBoard.getMineSweeperCell(i, j).isMine()) {
                    val position = Position(i, j)
                    list.add(position)
                }

            }

        }
        return list
    }

    private fun checkWinCondition() {
        if (gameBoard.allNonMinesCellsRevealed()) {
            _state.value = MinesWeeperGameState.GameWin
            gameWin.value = true
        }
    }

    private fun addRevealedCellsToListOnPress(position: Position, list: ArrayList<CellData>) {
        val cell = gameBoard.getMineSweeperCell(position.positionI, position.positionJ)
        val cellData = CellData(position, cell.amountOfAroundMines())

        if (!list.contains(cellData)) {
            if(!cell.isMarked()) list.add(cellData)
            if (cellData.counterMines == 0) {
                getAllAroundCells(position, list)
            }
        }
    }

    private fun getAllAroundCells(
        cellPosition: Position,
        listOfCells: ArrayList<CellData>
    ) {
        val fromRow = if (cellPosition.positionI > 0) {
            cellPosition.positionI - 1
        } else 0

        val fromColumn = if (cellPosition.positionJ > 0) {
            cellPosition.positionJ - 1
        } else 0

        val toRow = if (cellPosition.positionI < gameBoard.getRows().minus(1)) {
            cellPosition.positionI + 1
        } else gameBoard.getRows().minus(1)

        val toColumn = if (cellPosition.positionJ < gameBoard.getColumns().minus(1)) {
            cellPosition.positionJ + 1
        } else gameBoard.getColumns().minus(1)


        for (i in fromRow..toRow) {
            for (j in fromColumn..toColumn) {

                val position = Position(i, j)

                if (position.positionI != cellPosition.positionI ||
                    position.positionJ != cellPosition.positionJ
                ) {
                    addRevealedCellsToListOnPress(position, listOfCells)
                }

            }
        }
    }

}