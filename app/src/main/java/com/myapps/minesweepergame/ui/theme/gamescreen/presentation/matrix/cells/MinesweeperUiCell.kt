package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix.cells

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapps.minesweepergame.ui.theme.DarkerBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MinesweeperUiCell(
    width: Dp,
    height: Dp,
    backgroundColor: () -> Color,
    numberOfMines: () -> Int?,
    @DrawableRes
    icon: () -> Int?,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(
                width = width,
                height = height
            )
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(
                5.dp
            ),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(2.dp, DarkerBlue),
        colors = CardDefaults.cardColors(backgroundColor()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (numberOfMines() != null && numberOfMines() != 0) {
                Text(
                    text = numberOfMines().toString(),
                    color = Color.White
                )
            }
            icon()?.let { painterResource(id = it) }?.let {
                Image(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = it,
                    contentDescription = ""
                )
            }
        }
    }
}


