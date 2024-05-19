package com.myapps.minesweepergame.ui.theme.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapps.minesweepergame.ui.theme.gamescreen.GameController
import com.myapps.minesweepergame.ui.theme.gamescreen.GameControllerViewModel

class GameControllerViewModelFactory(
    private val gameController: GameController
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameControllerViewModel::class.java)) {
            return GameControllerViewModel(gameController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}