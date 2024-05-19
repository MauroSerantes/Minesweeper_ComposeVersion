package com.myapps.minesweepergame.ui.theme.mainscreen.menuselection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.mainscreen.difficultyselection.GameDifficultySelectionDialog
import com.myapps.minesweepergame.ui.theme.mainscreen.utilscomposables.NeoShadowButton

@Composable
fun SelectionMenu(
    modifier: Modifier,
    navController: NavController
) {

    var isDifficultySelectorOpen by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        NeoShadowButton(buttonText = "Casual Game", onClickButton = {
            isDifficultySelectorOpen = true
        })
        NeoShadowButton(
            buttonText = "Playing Modes",
            onClickButton = { },
            iconDefault = R.drawable.baseline_games_24
        )
        NeoShadowButton(
            buttonText = "History",
            onClickButton = { },
            iconDefault = R.drawable.history_icon
        )
    }

    if(isDifficultySelectorOpen){
        GameDifficultySelectionDialog(
            title = "Difficulty Selection",
            onDismiss = { isDifficultySelectorOpen = false },
            navController = navController
        )
    }
}