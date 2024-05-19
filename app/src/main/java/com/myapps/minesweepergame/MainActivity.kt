package com.myapps.minesweepergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.myapps.minesweepergame.ui.theme.BlueBackground
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.MinesweeperGameTheme
import com.myapps.minesweepergame.ui.theme.navigation.NavigationGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinesweeperGameTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = BrilliantBlue
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}
















