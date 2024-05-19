package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer


sealed interface TimerCommands{
    object StartTimer: TimerCommands
    object StopTimer: TimerCommands
    object ResetTimer: TimerCommands
}