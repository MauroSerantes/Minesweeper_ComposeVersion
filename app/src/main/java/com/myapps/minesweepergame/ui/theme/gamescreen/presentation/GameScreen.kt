package com.myapps.minesweepergame.ui.theme.gamescreen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer.TimerCommands
import com.myapps.minesweepergame.ui.theme.Azure
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.BrilliantRed
import com.myapps.minesweepergame.ui.theme.BrilliantYellow
import com.myapps.minesweepergame.ui.theme.DarkerBlue
import com.myapps.minesweepergame.ui.theme.LightBrilliantBlue
import com.myapps.minesweepergame.ui.theme.SoftBlue
import com.myapps.minesweepergame.ui.theme.gamescreen.MinesWeeperCellUiDetails
import com.myapps.minesweepergame.ui.theme.gamescreen.MinesWeeperGameState
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.ending.EndGameDialog
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix.Matrix
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.settings.GameScreenTopAppBar
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.settings.GameSettings
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position

@Composable
fun MinesweeperGameScreen(
    gameState: MinesWeeperGameState,
    amountOfRows: Int,
    amountOfColumns: Int,
    amountOfMines: Int,
    events: List<(Position) -> Unit>,
    navController: NavController,
    onReset: () -> Unit
) {
    var timerCommands by remember { mutableStateOf<TimerCommands>(TimerCommands.StartTimer) }
    var mines by remember { mutableStateOf(amountOfMines) }
    var showEndedGameMessage by remember { mutableStateOf(false) }
    var endGameMessage by remember { mutableStateOf("") }

    val updatingCells = remember { mutableStateListOf<MinesWeeperCellUiDetails>() }

    val onClickCells by rememberUpdatedState(events[0])
    val onLongClick by rememberUpdatedState(events[1])

    when (gameState) {
        is MinesWeeperGameState.Gaming -> {}

        is MinesWeeperGameState.GameLose -> {
            updatingCells.clear()
            gameState.listOfPositionsOfMines.forEach {
                updatingCells.add(
                    MinesWeeperCellUiDetails(
                        it.positionI,
                        it.positionJ,
                        backgroundColor = BrilliantRed,
                        numberOfMines = null,
                        icon = R.drawable.bomb_game,
                        flip = true
                    )
                )
            }
            timerCommands = TimerCommands.StopTimer
            showEndedGameMessage = true
            endGameMessage = "You Lose"
        }

        is MinesWeeperGameState.GameWin -> {
            timerCommands = TimerCommands.StopTimer
            showEndedGameMessage = true
            endGameMessage = "You Win"
        }

        is MinesWeeperGameState.MarkCell -> {
            updatingCells.clear()
            updatingCells.add(
                MinesWeeperCellUiDetails(
                    gameState.position.positionI,
                    gameState.position.positionJ,
                    backgroundColor = BrilliantYellow,
                    numberOfMines = null,
                    icon = R.drawable.flag_icon_red_4,
                    flip = false
                )
            )
            mines = gameState.flagCount
        }

        is MinesWeeperGameState.UnmarkedCell -> {
            updatingCells.clear()
            updatingCells.add(
                MinesWeeperCellUiDetails(
                    gameState.position.positionI,
                    gameState.position.positionJ,
                    backgroundColor = LightBrilliantBlue,
                    numberOfMines = null,
                    icon = null,
                    flip = false
                )
            )
            mines = gameState.flagCount
        }

        is MinesWeeperGameState.RevealNumberCells -> {
            updatingCells.clear()
            gameState.listOfPositions.forEach { cellData ->
                updatingCells.add(
                    MinesWeeperCellUiDetails(
                        cellData.position.positionI,
                        cellData.position.positionJ,
                        backgroundColor = DarkerBlue,
                        numberOfMines = cellData.counterMines,
                        icon = null,
                        flip = true
                    )
                )
            }
        }

        is MinesWeeperGameState.ResetGame -> {
            updatingCells.clear()
            for (i in 0 until amountOfRows) {
                for (j in 0 until amountOfColumns) {
                    updatingCells.add(
                        MinesWeeperCellUiDetails(
                            i,
                            j,
                            LightBrilliantBlue,
                            null,
                            null,
                            flip = false,
                        )
                    )
                }
            }
            mines = amountOfMines
            timerCommands = TimerCommands.ResetTimer
        }
    }

    Scaffold(
        topBar = {
            GameScreenTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                amountOfMines = { mines },
                timerCommands = { timerCommands }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .drawBehind {
                    drawRoundRect(DarkerBlue)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Matrix(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight()
                    .padding(5.dp),
                amountOfRows = { amountOfRows },
                amountOfColumns = { amountOfColumns },
                onClickCells = { onClickCells.invoke(it) },
                onLongClick = { onLongClick.invoke(it) },
                cellsUpdating = { updatingCells }
            )
        }
        if (showEndedGameMessage) {
            EndGameDialog(
                modifier = Modifier,
                message = endGameMessage,
                navController = navController,
                onDismiss = {
                    showEndedGameMessage = false
                },
                onRetry = onReset
            )
        }
    }
}


