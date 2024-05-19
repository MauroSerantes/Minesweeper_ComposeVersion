package com.myapps.minesweepergame.domain

fun interface MatrixTraverseDo<T,R> {
    fun matrixDo(element: T?,rowIndex:Int,columnIndex:Int,contextVariable:R?)
}