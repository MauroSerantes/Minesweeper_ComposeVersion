package com.myapps.minesweepergame.ui.theme.gamescreen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapps.minesweepergame.R
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.timer.TimerCommands
import com.myapps.minesweepergame.ui.theme.Azure
import com.myapps.minesweepergame.ui.theme.BrilliantBlue
import com.myapps.minesweepergame.ui.theme.BrilliantRed
import com.myapps.minesweepergame.ui.theme.BrilliantYellow
import com.myapps.minesweepergame.ui.theme.gamescreen.MinesWeeperCellUiDetails
import com.myapps.minesweepergame.ui.theme.gamescreen.MinesWeeperGameState
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.ending.EndGameDialog
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.matrix.Matrix
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.settings.GameSettings
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.Position


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    gameState: MinesWeeperGameState,
    amountOfRows: Int,
    amountOfColumns: Int,
    amountOfMines: Int,
    events: List<(Position) -> Unit>,
    navController: NavController,
    onReset: () -> Unit
) {

    var timerCommands by remember {
        mutableStateOf<TimerCommands>(TimerCommands.StartTimer)
    }

    var mines by remember {
        mutableStateOf(amountOfMines)
    }


    var updating by remember {
        mutableStateOf<List<MinesWeeperCellUiDetails>>(emptyList())
    }

    var showEndedGameMessage by remember {
        mutableStateOf(false)
    }

    var engGameMessage by remember {
        mutableStateOf("")
    }

    when (gameState) {
        is MinesWeeperGameState.Gaming -> {
        }

        is MinesWeeperGameState.GameLose -> {
            val list = ArrayList<MinesWeeperCellUiDetails>()
            gameState.listOfPositionsOfMines.forEach {
                val cellData = MinesWeeperCellUiDetails(
                    it.positionI,
                    it.positionJ,
                    backgroundColor = BrilliantRed,
                    numberOfMines = null,
                    icon = R.drawable.bomb_game
                )
                list.add(cellData)
            }
            updating = list
            timerCommands = TimerCommands.StopTimer
            showEndedGameMessage = true
            engGameMessage = "You Lose"
        }

        is MinesWeeperGameState.GameWin -> {
            timerCommands = TimerCommands.StopTimer
            showEndedGameMessage = true
            engGameMessage = "You Win"
        }

        is MinesWeeperGameState.MarkCell -> {
            val list = ArrayList<MinesWeeperCellUiDetails>()
            val dataCell = MinesWeeperCellUiDetails(
                gameState.position.positionI,
                gameState.position.positionJ,
                backgroundColor = BrilliantYellow,
                numberOfMines = null,
                icon = R.drawable.flag_icon_red_4
            )
            list.add(dataCell)
            updating = list
            mines = gameState.flagCount
        }

        is MinesWeeperGameState.UnmarkedCell -> {
            val list = ArrayList<MinesWeeperCellUiDetails>()
            val dataCell = MinesWeeperCellUiDetails(
                gameState.position.positionI,
                gameState.position.positionJ,
                backgroundColor = BrilliantBlue,
                numberOfMines = null,
                icon = null
            )
            list.add(dataCell)
            updating = list
            mines = gameState.flagCount
        }

        is MinesWeeperGameState.RevealNumberCells -> {
            val list = ArrayList<MinesWeeperCellUiDetails>()
            gameState.listOfPositions.forEach { cellData ->
                val dataCell = MinesWeeperCellUiDetails(
                    cellData.position.positionI,
                    cellData.position.positionJ,
                    backgroundColor = Azure,
                    numberOfMines = cellData.counterMines,
                    icon = null
                )
                list.add(dataCell)
            }
            updating = list
        }

        is MinesWeeperGameState.ResetGame -> {
            val list = ArrayList<MinesWeeperCellUiDetails>()
            for (i in 0..amountOfRows) {
                for (j in 0..amountOfColumns) {
                    list.add(
                        MinesWeeperCellUiDetails(i, j, BrilliantBlue, null, null)
                    )
                }
            }
            updating = list
            mines = amountOfMines
            timerCommands = TimerCommands.ResetTimer
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp)
    ) {

        GameSettings(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
            amountOfMines = { mines }) {
            timerCommands
        }

        Spacer(modifier = Modifier.height(20.dp))

        Matrix(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            amountOfRows = { amountOfRows },
            amountOfColumns = { amountOfColumns },
            onClickCells = {
                events[0].invoke(it)
            },
            onLongClick = {
                events[1].invoke(it)
            },
            cellsUpdating = {
                updating
            }
        )

        if (showEndedGameMessage) {
            EndGameDialog(
                modifier = Modifier,
                message = engGameMessage,
                navController = navController,
                onDismiss = {
                    showEndedGameMessage = false
                },
                onRetry = onReset
            )
        }
    }
}
