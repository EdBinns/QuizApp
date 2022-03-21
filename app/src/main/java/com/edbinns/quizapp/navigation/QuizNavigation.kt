package com.edbinns.quizapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edbinns.quizapp.screens.mainScreen.MainScreen
import com.edbinns.quizapp.screens.quizScreen.QuizScreen
import com.edbinns.quizapp.screens.resultScreen.ResultScreen
import com.edbinns.quizapp.screens.scoreScreen.ScoreScreen


@ExperimentalComposeUiApi
@Composable
fun QuizNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = QuizScreens.MainScreen.name) {

        composable(QuizScreens.MainScreen.name) {
            MainScreen(navController = navController)
        }
        composable(QuizScreens.QuizScreen.name) {
            QuizScreen(navController = navController)
        }
        composable(QuizScreens.ResultScreen.name) {
            ResultScreen(navController = navController)
        }

        val route = "${QuizScreens.ScoreScreen.name}/{score}"
        composable(route = route, arguments = listOf(
            navArgument(name = "score") {
                type = NavType.StringType
            })
        ) {navBack ->
            navBack.arguments?.getString("score")?.let { score ->
                ScoreScreen(navController = navController, score= score)
            }

        }
    }
}