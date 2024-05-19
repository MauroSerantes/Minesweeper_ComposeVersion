package com.myapps.minesweepergame.ui.theme.mainscreen.utilscomposables

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.SoftBlue
import com.myapps.minesweepergame.ui.theme.TransparentBlack

@Composable
fun NeoShadowButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClickButton: () -> Unit,
    @DrawableRes iconDefault: Int? = null,
    backgroundColor: Color = Color.White,
    textColor: Color = BrilliantBlue,
    iconTint: Color = BrilliantBlue
) {
    ElevatedButton(
        onClick = onClickButton,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(4.dp),
        colors = ButtonDefaults.elevatedButtonColors(backgroundColor),
        shape = RoundedCornerShape(10.dp)
    ) {
        if (iconDefault != null) {
            Icon(
                painter = painterResource(id = iconDefault),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Text(
            text = buttonText,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.1.em
        )
    }
}


@Composable
fun CustomButtonWithAnimatedText(
    modifier: Modifier,
    buttonTitle:String,
    textDescriptor: String,
    onClickButton: () -> Unit,
    onEnabled:()->Boolean
){

    Column(
        modifier = modifier
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClickButton,
            colors = if(onEnabled()) ButtonDefaults.buttonColors(SoftBlue)
            else  ButtonDefaults.buttonColors(DarkerBlue),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = buttonTitle,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(visible = onEnabled()) {
            Box(modifier = modifier
                .height(30.dp)
                .background(
                    color = TransparentBlack,
                    shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                ),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = textDescriptor,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}

