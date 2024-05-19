package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix.cells

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MinesweeperUiCell(
    modifier: Modifier = Modifier,
    backgroundColor: () -> Color,
    numberOfMines: () -> Int?,
    @DrawableRes icon: () -> Int?,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    isFlipped: Boolean,
    onFlip: (Boolean) -> Unit
) {
    val currentBackgroundColor by rememberUpdatedState(backgroundColor())
    val currentNumberOfMines by rememberUpdatedState(numberOfMines())
    val currentIcon by rememberUpdatedState(icon())

    val rotationState = remember { Animatable(if (isFlipped) 180f else 0f) }
    val coroutineScope = rememberCoroutineScope()

    var isInternalChange by remember { mutableStateOf(false) }

    fun flipCell(action: () -> Unit) {
        coroutineScope.launch {
            isInternalChange = true
            val newFlipState = !isFlipped
            onFlip(newFlipState)
            rotationState.animateTo(
                targetValue = if (newFlipState) 180f else 0f,
                animationSpec = tween(durationMillis = 300)
            )
            action()
            rotationState.snapTo(0f)
            isInternalChange = false
        }
    }

    LaunchedEffect(isFlipped) {
        if (!isInternalChange) {
            rotationState.animateTo(if (isFlipped) 180f else 0f)
        }
    }

    val cellModifier = modifier
        .combinedClickable(
            onClick = {
                flipCell {
                    onClick()
                }
            },
            onLongClick = {
                flipCell {
                    onLongClick()
                }
            }
        )
        .graphicsLayer(rotationY = rotationState.value)
        .onRevealedState(isFlipped, 4.dp)

    if (isFlipped) {
        Box(
            modifier = modifier
                .drawBehind {
                    drawRoundRect(currentBackgroundColor)
                },
            contentAlignment = Alignment.Center
        ) {
            val numberOfMinesText = currentNumberOfMines?.takeIf { it != 0 }?.toString()
            numberOfMinesText?.let {
                Text(text = it, color = Color.White)
            }
            val painter = currentIcon?.let { painterResource(id = it) }
            painter?.let {
                Image(
                    modifier = Modifier.padding(8.dp),
                    painter = it,
                    contentDescription = null
                )
            }
        }
    } else {
        val cellElevation = CardDefaults.elevatedCardElevation(8.dp)
        val cellShape = RoundedCornerShape(10.dp)
        val cellBorder = BorderStroke(1.dp, Color.Black)

        Card(
            modifier = cellModifier,
            elevation = cellElevation,
            shape = cellShape,
            border = cellBorder,
            colors = CardDefaults.cardColors(currentBackgroundColor),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val numberOfMinesText = currentNumberOfMines?.takeIf { it != 0 }?.toString()
                numberOfMinesText?.let {
                    Text(text = it, color = Color.White)
                }
                val painter = currentIcon?.let { painterResource(id = it) }
                painter?.let {
                    Image(
                        modifier = Modifier.padding(8.dp),
                        painter = it,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun Modifier.onRevealedState(isRevealed: Boolean, elevation: Dp): Modifier {
    return this.then(
        if (isRevealed) {
            Modifier.shadow(elevation, shape = RoundedCornerShape(10.dp))
        } else {
            Modifier
        }
    )
}