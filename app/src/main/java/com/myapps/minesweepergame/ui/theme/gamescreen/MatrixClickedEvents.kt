package com.myapps.minesweepergame.ui.theme.gamescreen

import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position

sealed interface MatrixClickedEvents{
    data class OnCellDig(val position: Position):MatrixClickedEvents
    data class OnCellMarked(val position: Position):MatrixClickedEvents
    object OnResetGame:MatrixClickedEvents
}