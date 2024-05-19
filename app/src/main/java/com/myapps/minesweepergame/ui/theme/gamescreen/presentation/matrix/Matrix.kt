package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import com.myapps.minesweepergame.ui.theme.SoftBlue
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

    val matrix = remember(amountOfRows(), amountOfColumns()) {
        com.myapps.minesweepergame.domain.Matrix<MinesWeeperCellUiDetails>(
            amountOfRows(), amountOfColumns()
        ).apply {
            for (i in 0 until this.getRows()) {
                for (j in 0 until this.getColumns()) {
                    this.insertElement(
                        MinesWeeperCellUiDetails(
                            i,
                            j,
                            LightBrilliantBlue,
                            null,
                            null,
                            flip = false
                        ),
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
            flip = detail.flip
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(amountOfRows()) { i ->
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(amountOfColumns()) { j ->
                    matrix.getElementByPosition(i, j)?.let { cell ->
                        MinesweeperUiCell(
                            modifier = Modifier
                                .fillParentMaxWidth(1f / amountOfColumns())
                                .aspectRatio(1f),
                            backgroundColor = { cell.backgroundColor },
                            numberOfMines = { cell.numberOfMines },
                            icon = { cell.icon },
                            onClick = {
                                onClickCells(Position(i, j))
                                matrix.getElementByPosition(i, j)?.flip = true
                            },
                            onLongClick = {
                                onLongClick(Position(i, j))
                                matrix.getElementByPosition(i, j)?.flip = !cell.flip
                            },
                            isFlipped = cell.flip,
                            onFlip = { newFlipState ->
                                matrix.getElementByPosition(i, j)?.flip = newFlipState
                            }
                        )
                    }
                }
            }
        }
    }
}

