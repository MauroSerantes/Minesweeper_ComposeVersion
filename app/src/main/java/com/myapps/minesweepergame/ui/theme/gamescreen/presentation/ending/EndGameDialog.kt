package com.myapps.minesweepergame.ui.theme.gamescreen.presentation.ending

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.somaticRoundedFontFamily
import com.myapps.minesweepergame.ui.theme.tekTurFontFamily

@Composable
fun EndGameDialog(
    modifier: Modifier,
    message: String,
    navController: NavController,
    onDismiss: () -> Unit,
    onRetry: () -> Unit
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(30.dp, 8.dp, 30.dp, 8.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    color = BrilliantBlue,
                    fontFamily = somaticRoundedFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp
                )
                Spacer(modifier = Modifier.height(25.dp))

                EndScreenButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    icon = null,
                    title = "Back to Main Screen"
                ) {
                    onDismiss.invoke()
                    navController.popBackStack()
                }
                Spacer(modifier = Modifier.height(10.dp))

                EndScreenButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    icon = null,
                    title = "Try Again"
                ) {
                    onDismiss.invoke()
                    onRetry.invoke()
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}

@Composable
fun EndScreenButton(
    modifier: Modifier,
    @DrawableRes
    icon: Int?,
    title: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(DarkerBlue),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            bottomEnd = 10.dp
        ),
        elevation = ButtonDefaults.buttonElevation(5.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            if (icon != null) Image(painter = painterResource(id = icon), contentDescription = "")
            Text(
                title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = tekTurFontFamily,
                color = Color.White
            )
        }
    }
}
