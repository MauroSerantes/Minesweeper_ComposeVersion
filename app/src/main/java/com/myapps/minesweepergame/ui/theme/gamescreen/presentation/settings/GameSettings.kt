package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.settings

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer.TimerCommands
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer.Timer

@Composable
fun GameSettings(
    modifier: Modifier,
    amountOfMines: () -> Int,
    timerCommands: () -> TimerCommands
) {
    Column(modifier = modifier) {

        FloatingActionButton(
            onClick = {},
            containerColor = LightBrilliantBlue,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height (25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameDetails(
                modifier = Modifier
                    .size(100.dp, 40.dp)
                    .background(
                        color = DarkerBlue,
                        shape = RoundedCornerShape(5.dp)
                    ),
                drawable = R.drawable.bomb
            ) {
                amountOfMines().toString()
            }
            Timer(
                modifier = Modifier
                    .size(100.dp, 40.dp)
                    .background(color = DarkerBlue, shape = RoundedCornerShape(5.dp)),
                drawable = R.drawable.timer
            ) {
                timerCommands()
            }
        }
    }
}


@Composable
fun GameDetails(
    modifier: Modifier,
    @DrawableRes
    drawable: Int,
    displayData: () -> String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = drawable), contentDescription = "", tint = Color.White)
        Text(
            text = displayData(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
