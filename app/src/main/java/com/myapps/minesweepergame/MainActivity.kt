package com.myapps.minesweepergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import com.myapps.minesweepergame.ui.theme.MinesweeperGameTheme
import com.myapps.minesweepergame.ui.theme.navigation.NavigationGraph
import com.myapps.minesweepergame.ui.theme.gamescreen.MinesWeeperCellUiDetails
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position
import com.myapps.minesweepergame.ui.theme.somaticRoundedFontFamily
import com.myapps.minesweepergame.ui.theme.tekTurFontFamily
import java.util.concurrent.TimeUnit

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
















