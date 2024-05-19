package com.myapps.minesweepergame.ui.theme.mainscreen.difficultyselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Difficulty.ADVANCED
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Difficulty.BEGINNER
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Difficulty.INTERMEDIATE
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.navigation.ScreenPath
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.SoftBlue
import com.myapps.minesweepergame.ui.theme.mainscreen.utilscomposables.CustomButtonWithAnimatedText
import com.myapps.minesweepergame.ui.theme.somaticRoundedFontFamily
import com.myapps.minesweepergame.ui.theme.tekTurFontFamily

@Composable
fun GameDifficultySelectionDialog(
    title:String?,
    onDismiss:()->Unit,
    navController: NavController
){
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(modifier = Modifier
            .height(550.dp)
        ) {
            Column{
                Spacer(modifier = Modifier.height(130.dp))
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
                            text = title!!,
                            textAlign = TextAlign.Center,
                            color = BrilliantBlue,
                            fontFamily = somaticRoundedFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(30.dp))


                        var enabledBeginner by remember{
                            mutableStateOf(false)
                        }

                        var enabledIntermediate by remember{
                            mutableStateOf(false)
                        }

                        var enabledAdvanced by remember{
                            mutableStateOf(false)
                        }


                        CustomButtonWithAnimatedText(
                            modifier = Modifier.fillMaxWidth(),
                            buttonTitle = "Beginner",
                            textDescriptor = "64 tiles and 10 mines",
                            onClickButton = {
                                enabledBeginner = !enabledBeginner
                                enabledIntermediate = false
                                enabledAdvanced = false }
                        ) { enabledBeginner }

                        Spacer(modifier = Modifier.height(10.dp))

                        CustomButtonWithAnimatedText(
                            modifier = Modifier.fillMaxWidth(),
                            buttonTitle = "Intermediate",
                            textDescriptor = "256 tiles and 40 mines",
                            onClickButton = {
                                enabledBeginner = false
                                enabledIntermediate = !enabledIntermediate
                                enabledAdvanced = false }
                        ) { enabledIntermediate }

                        Spacer(modifier = Modifier.height(10.dp))

                        CustomButtonWithAnimatedText(
                            modifier = Modifier.fillMaxWidth(),
                            buttonTitle = "Advanced",
                            textDescriptor = "480 tiles and 99 mines",
                            onClickButton = {
                                enabledBeginner = false
                                enabledIntermediate = false
                                enabledAdvanced = !enabledAdvanced}
                        ) { enabledAdvanced }

                        Spacer(modifier = Modifier.height(20.dp))


                        Button(
                            onClick = {
                                if(enabledBeginner){
                                    navController.navigate(ScreenPath.GameScreen.route+"/${BEGINNER}")
                                    return@Button
                                }
                                if(enabledIntermediate){
                                    navController.navigate(ScreenPath.GameScreen.route+"/${INTERMEDIATE}")
                                    return@Button
                                }
                                if(enabledAdvanced){
                                    navController.navigate(ScreenPath.GameScreen.route+"/${ADVANCED}")
                                    return@Button
                                }
                            },
                            colors = if(enabledBeginner || enabledIntermediate || enabledAdvanced) ButtonDefaults.buttonColors(
                                SoftBlue
                            )
                            else  ButtonDefaults.buttonColors(DarkerBlue),
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        ){
                            Text("PLAY",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = tekTurFontFamily,
                                color = Color.White
                            )
                        }

                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.bomb_minesweeper),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 40.dp)
            )
        }
    }
}
