package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import com.myapps.minesweepergame.ui.theme.gamescreen.MinesWeeperCellUiDetails
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix.cells.MinesweeperUiCell
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position

@Composable
fun Matrix(
    modifier: Modifier,
    amountOfRows: () -> Int,
    amountOfColumns: () -> Int,
    onClickCells: (Position) -> Unit,
    onLongClick: (Position) -> Unit,
    cellsUpdating: () -> List<MinesWeeperCellUiDetails>
) {

    val matrix = remember {
        com.myapps.minesweepergame.domain.Matrix<MinesWeeperCellUiDetails>(
            amountOfRows(), amountOfColumns()
        ).apply {
            for (i in 0 until this.getRows()) {
                for (j in 0 until this.getColumns()) {
                    this.insertElement(
                        MinesWeeperCellUiDetails(i, j, LightBrilliantBlue, null, null),
                        i,
                        j
                    )
                }
            }
        }
    }


    val list = cellsUpdating.invoke()
    list.forEach { detail ->
        val element = matrix.getElementByPosition(detail.rowIndex, detail.columnIndex)
        element?.apply {
            backgroundColor = detail.backgroundColor
            numberOfMines = detail.numberOfMines
            icon = detail.icon
        }
    }



    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        repeat(amountOfRows()) { i ->
            Row {
                repeat(amountOfColumns()) { j ->
                    MinesweeperUiCell(
                        width = 48.dp,
                        height = 48.dp,
                        backgroundColor = { matrix.getElementByPosition(i, j)?.backgroundColor!! },
                        numberOfMines = { matrix.getElementByPosition(i, j)?.numberOfMines },
                        icon = { matrix.getElementByPosition(i, j)?.icon },
                        onClick = {
                            onClickCells(Position(i, j))
                        },
                        onLongClick = {
                            onLongClick(Position(i, j))
                        }
                    )
                }
            }
        }
    }
}
