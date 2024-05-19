package com.myapps.minesweepergame.ui.theme.gamescreen.utils

import com.myapps.minesweepergame.ui.theme.gamescreen.GameModeDetails

fun getGameDetailsBasedOnDifficulty(difficulty: Int): GameModeDetails? {
    return when (difficulty) {
        Difficulty.BEGINNER -> {
            GameModeDetails(8, 8, 10)
        }

        Difficulty.INTERMEDIATE -> {
            GameModeDetails(32, 8, 40)
        }

        Difficulty.ADVANCED -> {
            GameModeDetails(60, 8, 99)
        }

        else -> {
            null
        }
    }
}