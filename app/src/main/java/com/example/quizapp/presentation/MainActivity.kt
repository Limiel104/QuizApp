package com.example.quizapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizapp.presentation.questions.composable.QuestionsScreen
import com.example.quizapp.presentation.results.composable.ResultsScreen
import com.example.quizapp.presentation.select_category.composable.SelectCategoryScreen
import com.example.quizapp.presentation.select_difficulty.composable.SelectDifficultyScreen
import com.example.quizapp.presentation.stats.composable.StatsScreen
import com.example.quizapp.ui.theme.*
import com.example.quizapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SelectCategoryScreen.route
                    ) {
                        composable(
                            route = Screen.SelectCategoryScreen.route
                        ) {
                            SelectCategoryScreen(navController = navController)
                        }
                        composable(
                            route = Screen.SelectDifficultyScreen.route + "category={category}",
                            arguments = listOf(
                                navArgument(
                                    name = "category"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            SelectDifficultyScreen(navController = navController)
                        }
                        composable(
                            route = Screen.QuestionsScreen.route + "category={category}&difficulty={difficulty}",
                            arguments = listOf(
                                navArgument(
                                    name = "category"
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "difficulty"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            QuestionsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ResultsScreen.route + "result={result}&time={time}&category={category}",
                            arguments = listOf(
                                navArgument(
                                    name = "result"
                                ) {
                                    type = NavType.IntType
                                },
                                navArgument(
                                    name = "time"
                                ) {
                                    type = NavType.IntType
                                },
                                navArgument(
                                    name = "category"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            ResultsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.StatsScreen.route + "category={category}",
                            arguments = listOf(
                                navArgument(
                                    name = "category"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            StatsScreen()
                        }
                    }
                }
            }
        }
    }
}


