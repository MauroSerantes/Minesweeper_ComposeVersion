package com.myapps.minesweepergame.ui.theme.gamescreen

import com.myapps.minesweepergame.ui.theme.gamescreen.utils.CellData
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position


sealed class MinesWeeperGameState{
    object Gaming:MinesWeeperGameState()
    data class GameLose(val listOfPositionsOfMines:List<Position>):MinesWeeperGameState()
    object GameWin:MinesWeeperGameState()
    data class RevealNumberCells(val listOfPositions:List<CellData>):MinesWeeperGameState()
    data class MarkCell(val position: Position, val flagCount:Int):MinesWeeperGameState()
    data class UnmarkedCell(val position: Position, val flagCount: Int):MinesWeeperGameState()
    object ResetGame:MinesWeeperGameState()
}
