package com.edbinns.quizapp.screens.scoreScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.edbinns.quizapp.R
import com.edbinns.quizapp.components.CustomButton
import com.edbinns.quizapp.components.CustomInput
import com.edbinns.quizapp.components.QuizAppBar
import com.edbinns.quizapp.models.Result
import com.edbinns.quizapp.navigation.QuizScreens


@ExperimentalComposeUiApi
@Composable
fun ScoreScreen(
    navController: NavController,
    viewModel: ScoreViewModel = hiltViewModel(),
    score: String,
) {

    Scaffold(
        topBar = {
            QuizAppBar(
                navController = navController,
                title = "Score",
            ){
                navController.navigate(QuizScreens.MainScreen.name)
            }
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFF110035)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                ScoreWindow(viewModel, navController,score)
            }

        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun ScoreWindow(viewModel: ScoreViewModel, navController: NavController, score: String) {
    var name by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .padding(20.dp)
            .height(500.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF371E6F),
        elevation = 5.dp
    ) {

        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.congratulation),
                contentDescription = "Congrats Icon",
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp))

            Spacer(modifier = Modifier.height(20.dp))
            PlainText(text = "Tu puntuación es de un total:")
            ScoreText(data = score)

            Spacer(modifier = Modifier.height(20.dp))
            PlainText(text = "Ingresa tu nombre:")
            CustomInput(
                title = name,
                label = "Nombre",
                onTextChange = {
                    name = it
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomButton(text = "Agregar Resultado") {
                viewModel.addResult(Result(id = 0, username = name, totalPoints = score.toInt()))
                navController.navigate(QuizScreens.MainScreen.name)
            }
        }
    }
}

@Composable
fun ScoreText(data: String) {

    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            ) {
                append("$data ")
            }

            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            ) {
                append("puntos")
            }
        }
    }, modifier = Modifier.padding(2.dp))
}


@Composable
fun PlainText(text: String) {
    Text(text = text,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(2.dp))
}