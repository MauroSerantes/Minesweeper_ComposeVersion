package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.settings

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer.TimerCommands
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer.Timer

@Composable
fun GameSettings(
    modifier: Modifier = Modifier,
    amountOfMines: () -> Int,
    timerCommands: () -> TimerCommands
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GameDetails(
            drawable = R.drawable.bomb,
            displayData = {
                amountOfMines().toString()
            }
        )
        Timer(
            drawable = R.drawable.timer,
            timerCommands = {
                timerCommands()
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenTopAppBar(
    modifier: Modifier = Modifier,
    amountOfMines: () -> Int,
    timerCommands: () -> TimerCommands,
    containerColor: Color = BrilliantBlue,
    contentColor: Color = Color.White
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameDetails(
                    drawable = R.drawable.bomb,
                    displayData = {
                        amountOfMines().toString()
                    }
                )
                Timer(
                    drawable = R.drawable.timer,
                    timerCommands = {
                        timerCommands()
                    }
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            navigationIconContentColor = contentColor,
            titleContentColor = contentColor
        )
    )
}


@Composable
fun GameDetails(
    modifier: Modifier = Modifier,
    @DrawableRes
    drawable: Int,
    displayData: () -> String,
    contentColor: Color = BrilliantBlue,
    containerColor: Color = Color.White
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = "",
            )
            Text(
                text = displayData(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
