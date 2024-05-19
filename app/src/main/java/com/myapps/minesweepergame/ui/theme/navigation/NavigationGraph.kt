package com.myapps.minesweepergame.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapps.minesweepergame.domain.MinesweeperBoard
import com.myapps.minesweepergame.ui.theme.gamescreen.GameControllerViewModel
import com.myapps.minesweepergame.ui.theme.gamescreen.MatrixClickedEvents
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.GameScreen
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.getGameDetailsBasedOnDifficulty
import com.myapps.minesweepergame.ui.theme.mainscreen.MainScreen

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenPath.MainScreen.route
    ){
        composable(route = ScreenPath.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(
            route = ScreenPath.GameScreen.route +"/{difficulty}",
            arguments = listOf(
                navArgument("difficulty"){
                    type = NavType.IntType
                }
            )
        ){
            backstackEntry ->
            backstackEntry.arguments?.getInt("difficulty")?.let { difficulty ->
                val gameModeDetails = remember {
                    getGameDetailsBasedOnDifficulty(difficulty)
                }
                val gameControllerState = remember{
                    GameControllerViewModel(
                        MinesweeperBoard(
                            gameModeDetails?.amountOfMines!!,
                            gameModeDetails.amountOfRows,
                            gameModeDetails.amountOfColumns
                        )
                    )
                }
                GameScreen(
                    gameControllerState.state.collectAsState().value,
                    gameModeDetails?.amountOfRows!!,
                    gameModeDetails.amountOfColumns,
                    gameModeDetails.amountOfMines,
                    listOf(
                        {pos->
                            gameControllerState.onEvent(MatrixClickedEvents.OnCellDig(pos))
                        },
                        {pos->
                            gameControllerState.onEvent(MatrixClickedEvents.OnCellMarked(pos))
                        }
                    ),
                    navController
                ){
                    gameControllerState.onEvent(MatrixClickedEvents.OnResetGame)
                }
            }
        }
    }
}
