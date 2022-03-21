package com.edbinns.quizapp.screens.resultScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.edbinns.quizapp.components.QuizAppBar
import com.edbinns.quizapp.models.Result
import com.edbinns.quizapp.navigation.QuizScreens


@Composable
fun ResultScreen(navController: NavController, viewModel: ResultsViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            QuizAppBar(
                navController = navController,
                title = "Resultados",
            ) {
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
            val list = viewModel.resultList.collectAsState().value
            LazyColumn {
                items(list.size) { index ->
                    ResultRow(list[index], position = (index + 1))
                }
            }
        }
    }
}

@Composable
fun ResultRow(result: Result, position: Int) {

    Surface(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(15.dp),
        color = Color(0xFF8036E9),
        elevation = 5.dp
    ) {

        Row(
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Surface(
                modifier = Modifier
                    .padding(1.dp)
                    .size(30.dp),
                shape = CircleShape,
                color = when (position) {
                    1 -> Color(0xFFFFBF00)
                    2 -> Color(0xFFE3E4E5)
                    3 -> Color(0xFFCD7F32)
                    else -> Color.White
                }
            ) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = position.toString(), color = Color.Black)
                }
            }

            Text(text = result.username,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

            Text(text = result.totalPoints.toString(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp)

        }
    }

}
