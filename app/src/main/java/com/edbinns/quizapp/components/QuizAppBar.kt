package com.edbinns.quizapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun QuizAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isQuizScreen: Boolean = false,
    isMainScreen: Boolean = false,
    elevation: Dp = 0.dp,
    navController: NavController,
    time: MutableState<String> = mutableStateOf(""),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},

    ) {


    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            )
        },
        actions = {
            if (isQuizScreen) {
                Surface(
                    modifier = Modifier
                        .padding(1.dp)
                        .size(30.dp),
                    shape = CircleShape,
                    color = Color.White
                ) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = time.value, color = Color(0xFFF33434))
                    }
                }
            }
        },
        navigationIcon = {
            if (!isMainScreen) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })
            }
        },
        backgroundColor = Color(0xFF371E6F),
        elevation = elevation
    )
}