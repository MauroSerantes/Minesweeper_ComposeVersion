package com.myapps.minesweepergame.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapps.minesweepergame.domain.MinesweeperBoard
import com.myapps.minesweepergame.ui.theme.gamescreen.GameController
import com.myapps.minesweepergame.ui.theme.gamescreen.GameControllerViewModel
import com.myapps.minesweepergame.ui.theme.gamescreen.GameStateManagement
import com.myapps.minesweepergame.ui.theme.gamescreen.MatrixClickedEvents
import com.myapps.minesweepergame.ui.theme.gamescreen.presentation.MinesweeperGameScreen
import com.myapps.minesweepergame.ui.theme.gamescreen.utils.getGameDetailsBasedOnDifficulty
import com.myapps.minesweepergame.ui.theme.mainscreen.MainScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenPath.MainScreen.route
    ) {
        composable(route = ScreenPath.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = ScreenPath.GameScreen.route + "/{difficulty}",
            arguments = listOf(
                navArgument("difficulty") {
                    type = NavType.IntType
                }
            )
        ) { backstackEntry ->
            val difficulty = backstackEntry.arguments?.getInt("difficulty")

            difficulty?.let { level ->
                val gameModeDetails = remember {
                    getGameDetailsBasedOnDifficulty(level)
                }

                gameModeDetails?.let { details ->
                    val minesweeperBoard = remember(details) {
                        MinesweeperBoard(
                            details.amountOfMines,
                            details.amountOfRows,
                            details.amountOfColumns
                        )
                    }
                    val gameStateManagement = remember(minesweeperBoard) {
                        GameStateManagement(minesweeperBoard)
                    }
                    val gameController = remember(gameStateManagement) {
                        GameController(gameStateManagement)
                    }

                    val gameControllerViewModel: GameControllerViewModel = viewModel(
                        factory = GameControllerViewModelFactory(gameController)
                    )

                    MinesweeperGameScreen(
                        gameState = gameControllerViewModel.gameState.collectAsState().value,
                        amountOfRows = details.amountOfRows,
                        amountOfColumns = details.amountOfColumns,
                        amountOfMines = details.amountOfMines,
                        events = listOf(
                            { pos ->
                                gameControllerViewModel.onEvent(
                                    MatrixClickedEvents.OnCellDig(
                                        pos
                                    )
                                )
                            },
                            { pos ->
                                gameControllerViewModel.onEvent(
                                    MatrixClickedEvents.OnCellMarked(
                                        pos
                                    )
                                )
                            }
                        ),
                        navController = navController
                    ) {
                        gameControllerViewModel.onEvent(MatrixClickedEvents.OnResetGame)
                    }
                }
            }
        }
    }
}