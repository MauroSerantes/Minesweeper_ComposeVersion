package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit


@Composable
fun Timer(
    modifier: Modifier = Modifier,
    @DrawableRes
    drawable: Int,
    timerCommands: () -> TimerCommands,
    contentColor:Color = BrilliantBlue,
    containerColor:Color = Color.White
) {

    var time by remember {
        mutableStateOf(0L)
    }

    var isRunning by remember {
        mutableStateOf(false)
    }

    var isReset by remember {
        mutableStateOf(false)
    }

    when (timerCommands()) {
        is TimerCommands.StartTimer -> {
            isRunning = true
            isReset = false
        }

        is TimerCommands.StopTimer -> {
            isRunning = false
            isReset = false
        }

        is TimerCommands.ResetTimer -> {
            isRunning = true
            isReset = true
        }
    }

    LaunchedEffect(isRunning) {
        if (isReset) time = 0
        while (isRunning) {
            delay(1000)
            time += 1000
        }
    }


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
                text = formatTime(time = time),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun formatTime(time: Long): String {

    val minutes = TimeUnit.MILLISECONDS.toMinutes(time) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(time) % 60

    return String.format("%02d:%02d", minutes, seconds)
}
