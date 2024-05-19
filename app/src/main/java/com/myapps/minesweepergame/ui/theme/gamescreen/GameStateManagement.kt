package com.myapps.minesweepergame.ui.theme.gamescreen

import com.myapps.minesweepergame.domain.MinesweeperBoard
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position

class GameStateManagement(private val gameBoard: MinesweeperBoard) {
    private var flagCounter = gameBoard.amountOfMines


    var isGameWon = false
        private set
    var isGameLost = false
        private set

    fun revealCell(position: Position): MinesWeeperGameState {
        val cell = gameBoard.getMineSweeperCell(position.positionI, position.positionJ)

        if (cell.isRevealed() || cell.isMarked()) {
            return MinesWeeperGameState.Gaming
        }

        cell.setRevealed()

        return if (cell.isMine()) {
            isGameLost = true
            MinesWeeperGameState.GameLose(gameBoard.getLocationOfAllMines())
        } else {
            val revealedCells = gameBoard.revealedCellsFromPosition(position)
            if (checkWinCondition()) {
                isGameWon = true
                MinesWeeperGameState.GameWin
            }
            MinesWeeperGameState.RevealNumberCells(revealedCells)
        }
    }

    fun toggleMarkCell(position: Position): MinesWeeperGameState {
        val cell = gameBoard.getMineSweeperCell(position.positionI, position.positionJ)

        if (cell.isRevealed()) return MinesWeeperGameState.Gaming

        return if (cell.isMarked()) {
            cell.setMarked(false)
            flagCounter += 1
            MinesWeeperGameState.UnmarkedCell(position, flagCounter)
        } else {
            if (flagCounter > 0) {
                cell.setMarked(true)
                flagCounter -= 1
                MinesWeeperGameState.MarkCell(position, flagCounter)
            } else {
                MinesWeeperGameState.Gaming
            }
        }
    }

    private fun checkWinCondition(): Boolean {
        if (gameBoard.allNonMinesCellsRevealed()) {
            return true
        }
        return false
    }

    fun resetGame() {
        gameBoard.resetBoard()
        isGameLost = false
        isGameWon = false
        flagCounter = gameBoard.amountOfMines
    }
}