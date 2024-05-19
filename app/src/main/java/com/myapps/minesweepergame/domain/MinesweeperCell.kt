package com.myapps.minesweepergame.domain


class MinesweeperCell(
    private val associatedMatrix: Matrix<MinesweeperCell>,
    val rowIndexLocation: Int,
    val columnIndexLocation: Int
) {
    var minesCount: Int = 0
        private set
    private var isMarked: Boolean = false
    private var isMine: Boolean = false
    private var isRevealed: Boolean = false

    init {
        associatedMatrix.insertElement(this, rowIndexLocation, columnIndexLocation)
    }

    fun setMarked(value:Boolean) {
        isMarked = value
    }

    fun setRevealed() {
        if (isRevealed.not()) {

            isRevealed = true

            if (minesCount == 0 && !isMine) {
                revealAroundCells()
            }
        }
    }

    fun setMine() {
        isMine = !isMine
    }

    fun amountOfAroundMines(): Int = minesCount

    fun isMarked(): Boolean = isMarked

    fun isMine(): Boolean = isMine

    fun isRevealed(): Boolean = isRevealed
    fun updateAroundMinesCount(){
        iterateAroundCell {cell ->
            if(cell?.isMine() == true){
                this.minesCount += 1
            }
        }
    }

    private fun revealAroundCells() {
        val queue = ArrayDeque<MinesweeperCell>()
        val processedCells = mutableSetOf<MinesweeperCell>()
        queue.add(this)
        processedCells.add(this)

        while (queue.isNotEmpty()) {
            val currentCell = queue.removeFirst()

            if (!currentCell.isRevealed()) {
                currentCell.setRevealed()
                if (currentCell.amountOfAroundMines() == 0) {
                    currentCell.iterateAroundCell { neighborCell ->
                        if (neighborCell != null && !neighborCell.isRevealed() && !neighborCell.isMarked() && processedCells.add(neighborCell)) {
                            queue.add(neighborCell)
                        }
                    }
                }
            }
        }
    }


    fun resetCell() {
        isMarked = false
        isMine = false
        isRevealed = false
        minesCount = 0
    }


    private fun iterateAroundCell(action: (MinesweeperCell?) -> Unit) {
        val fromRow = maxOf(0,rowIndexLocation-1)
        val toRow = minOf(associatedMatrix.getRows()-1,rowIndexLocation+1)
        val fromColumn = maxOf(0,columnIndexLocation-1)
        val toColumn = minOf(associatedMatrix.getColumns()-1 ,columnIndexLocation+1)

        for (i in fromRow .. toRow){
            for(j in fromColumn .. toColumn){
                if (i != rowIndexLocation || j != columnIndexLocation) {
                    action(associatedMatrix.getElementByPosition(i, j))
                }
            }
        }
    }
}