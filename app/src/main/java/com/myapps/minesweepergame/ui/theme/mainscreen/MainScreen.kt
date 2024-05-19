package com.myapps.minesweepergame.ui.theme.mainscreen

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.mainscreen.menuselection.SelectionMenu
import com.myapps.minesweepergame.ui.theme.tekTurFontFamily

@Composable
fun MainScreen(
    navController: NavController
){
    var imagePosition =  remember{
        Offset(0f,0f)
    }

    val bounceAnimation = rememberInfiniteTransition(label = "")

    val yAnimation by bounceAnimation.animateFloat(
        initialValue = 0f,
        targetValue = -80f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 400,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    imagePosition = Offset(imagePosition.x,yAnimation)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.End)
                .size(80.dp, 80.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = "",
                tint = Color.White
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bomb_minesweeper),
                contentDescription = null,
                modifier = Modifier
                    .size(
                        165.dp,
                        165.dp
                    )
                    .offset {
                        IntOffset(0,imagePosition.y.toInt())
                    }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "MineSweeper",
                fontFamily = tekTurFontFamily,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
        SelectionMenu(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 50.dp),
            navController = navController
        )
    }
}
