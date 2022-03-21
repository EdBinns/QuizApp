package com.edbinns.quizapp.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edbinns.quizapp.components.CustomButton
import com.edbinns.quizapp.components.QuizAppBar
import com.edbinns.quizapp.navigation.QuizScreens

@Composable
fun MainScreen(navController: NavController) {


    Scaffold(topBar = {
        QuizAppBar(title = "Movies Quiz", navController = navController, isMainScreen = true)
    }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFF110035)
        ) {
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {



                CustomButton(text = "Iniciar Quiz") {
                    navController.navigate(QuizScreens.QuizScreen.name)
                }

                CustomButton(text = "Resultados", isStartBtn = false) {
                    navController.navigate(QuizScreens.ResultScreen.name)
                }
            }
        }
    }

}


