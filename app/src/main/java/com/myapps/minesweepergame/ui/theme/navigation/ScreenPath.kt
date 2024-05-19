package com.myapps.minesweepergame.ui.theme.navigation

sealed class ScreenPath(val route:String){
    object MainScreen: ScreenPath("main_screen")
    object GameScreen: ScreenPath("game_screen")
}