package com.myapps.minesweepergame.ui.theme.gamescreen

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class MinesWeeperCellUiDetails(
    val rowIndex:Int,
    val columnIndex:Int,
    var backgroundColor: Color,
    @DrawableRes
    var icon:Int?,
    var numberOfMines:Int?,
    var flip:Boolean
)
