package com.myapps.minesweepergame.domain

import androidx.compose.runtime.Composable

class Matrix<T>(private var rows: Int, private var columns: Int) {

    private var matrix: MutableList<T?>

    init {
        matrix = if (rows >= 0 && columns >= 0) {
            val totalSize = rows * columns
            MutableList(totalSize) { null }
        } else {
            mutableListOf()
        }
    }

    fun getRows(): Int = rows

    fun getColumns(): Int = columns

    fun insertElement(element: T, rowIndex: Int, columnIndex: Int) {
        if (rowIndex in 0 until rows && columnIndex in 0 until columns) {
            val index = rowIndex * columns + columnIndex
            matrix[index] = element
        }
    }

    fun getElementByPosition(rowIndex: Int, columnIndex: Int): T? {
        if (rowIndex in 0 until rows && columnIndex in 0 until columns) {
            val index = rowIndex * columns + columnIndex
            return matrix[index]
        }
        return null
    }

    fun addRow() {
        rows += 1
        val newSizeArray = rows * columns
        matrix = (matrix + MutableList(columns) { null }).toMutableList()
    }

    fun addColumn() {
        columns += 1
        val newSizeArray = rows * columns
        val newMatrix = MutableList<T?>(newSizeArray) { null }

        for (i in 0 until rows) {
            for (j in 0 until columns - 1) {
                val oldIndex = i * (columns - 1) + j
                val newIndex = i * columns + j
                newMatrix[newIndex] = matrix[oldIndex]
            }
        }
        matrix = newMatrix
    }

    fun deleteRow(rowIndex: Int): Boolean {
        if (rowIndex < 0 || rowIndex >= rows) return false

        for (i in rowIndex * columns until (rows - 1) * columns) {
            matrix[i] = matrix[i + columns]
        }

        rows -= 1
        matrix = matrix.dropLast(columns).toMutableList()
        return true
    }

    fun deleteColumn(columnIndex: Int): Boolean {
        if (columnIndex < 0 || columnIndex >= columns) return false

        for (i in 0 until rows) {
            for (j in columnIndex until columns - 1) {
                matrix[i * columns + j] = matrix[i * columns + j + 1]
            }
        }

        columns -= 1
        matrix = matrix.filterIndexed { index, _ -> (index + 1) % (columns + 1) != 0 }.toMutableList()
        return true
    }

    fun traverse(action: (T?, Int, Int) -> Unit) {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                action(getElementByPosition(i, j), i, j)
            }
        }
    }
}