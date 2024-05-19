package com.myapps.minesweepergame.domain

import com.myapps.minesweepergame.ui.theme.gamescreen.utils.CellData
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position


class MinesweeperBoard(val amountOfMines: Int, amountOfRows: Int, amountOfColumns: Int) {

    private val cells: Matrix<MinesweeperCell> = Matrix(amountOfRows, amountOfColumns)

    init {
        for (i in 0 until amountOfRows) {
            for (j in 0 until amountOfColumns) {
                MinesweeperCell(cells, i, j)
            }
        }
        placeMines()
        updateAllCellsMinesCount()
    }

    fun getMineSweeperCell(rowId: Int, columnId: Int): MinesweeperCell {
        return cells.getElementByPosition(rowId, columnId)!!
    }

    fun getRows(): Int = cells.getRows()

    fun getColumns(): Int = cells.getColumns()

    fun allNonMinesCellsRevealed(): Boolean {
        var i = 0
        var j: Int
        var allRevealed = true
        while (i < cells.getRows()) {
            j = 0
            while (j < cells.getColumns() && allRevealed) {
                val cell = cells.getElementByPosition(i, j)
                if (!cell?.isRevealed()!! && !cell.isMine()) {
                    allRevealed = false
                }
                j++
            }
            i = if (!allRevealed) { cells.getRows() } else { i + 1 }
        }
        return allRevealed
    }


    fun resetBoard() {
        for (i in 0 until cells.getRows()) {
            for (j in 0 until cells.getColumns()) {
                val cell = cells.getElementByPosition(i, j)
                cell?.resetCell()
            }
        }
        placeMines()
        updateAllCellsMinesCount()
    }

    fun getLocationOfAllMines():List<Position>{
        val list = mutableListOf<Position>()
        for (i in 0 until this.getRows()) {
            for (j in 0 until this.getColumns()) {
                if (this.getMineSweeperCell(i, j).isMine()) {
                    val position = Position(i, j)
                    list.add(position)
                }
            }
        }
        return list
    }

    private fun placeMines() {
        var remainingMines = amountOfMines

        while (remainingMines > 0) {
            val i = (0 until cells.getRows()).random()
            val j = (0 until cells.getColumns()).random()
            val cell = cells.getElementByPosition(i, j)
            if (cell != null && !cell.isMine()) {
                cell.setMine()
                remainingMines--
            }
        }
    }

    private fun updateAllCellsMinesCount(){
        for (i in 0 until cells.getRows()) {
            for (j in 0 until cells.getColumns()) {
                cells.getElementByPosition(i, j)?.updateAroundMinesCount()
            }
        }
    }

    fun revealedCellsFromPosition(position: Position): MutableList<CellData> {
        val revealedCells = mutableListOf<CellData>()
        val stack = mutableListOf<Position>()
        stack.add(position)

        while (stack.isNotEmpty()) {
            val currentPosition = stack.removeAt(stack.size - 1)
            val cell = getMineSweeperCell(currentPosition.positionI, currentPosition.positionJ)

            // Si la celda ya está marcada o revelada, la ignoramos
            if (!cell.isMarked()) {
                cell.setRevealed()  // Marcar la celda como revelada
                val cellData = CellData(currentPosition, cell.minesCount)
                revealedCells.add(cellData)

                // Si no hay minas alrededor, revelamos las celdas adyacentes
                if (cellData.counterMines == 0) {
                    for (i in -1..1) {
                        for (j in -1..1) {
                            if (i == 0 && j == 0) continue // Ignorar la celda actual
                            val newRow = currentPosition.positionI + i
                            val newCol = currentPosition.positionJ + j

                            if (newRow in 0 until getRows() && newCol in 0 until getColumns()) {
                                val neighborCell = getMineSweeperCell(newRow, newCol)

                                // Solo añadir si no está revelada ni marcada
                                if (!neighborCell.isRevealed() && !neighborCell.isMarked()) {
                                    stack.add(Position(newRow, newCol))
                                }
                            }
                        }
                    }
                }
            }
        }
        return revealedCells
    }
}