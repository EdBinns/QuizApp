package com.edbinns.quizapp.screens.quizScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.edbinns.quizapp.components.CustomButton
import com.edbinns.quizapp.components.QuizAppBar
import com.edbinns.quizapp.models.Question
import com.edbinns.quizapp.navigation.QuizScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val questionCount = remember { mutableStateOf(1) }
    val question = viewModel.actualQuestion.value
    val timeQuestion = viewModel.time
    val totalPoints = remember { mutableStateOf(0) }
    val finishTime = remember { mutableStateOf(false) }
    val composableScope = rememberCoroutineScope()
    if (timeQuestion.value == "0") {
        composableScope.launch {
            Toast.makeText(context, "Se ha acabado el tiempo", Toast.LENGTH_LONG).show()
            finishTime.value = true
            delay(3000)
            nextQuestion(viewModel, questionCount)
        }

    }
    Scaffold(
        topBar = {
            QuizAppBar(navController = navController,
                title = "Question ${questionCount.value}",
                isQuizScreen = true,
                time = timeQuestion){
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
            question?.let { value ->
                QuestionBody(viewModel, value, totalPoints,timeQuestion,finishTime) {
                    if(questionCount.value == 10){
                        val route = "${QuizScreens.ScoreScreen.name}/${totalPoints.value.toString()}"
                        navController.navigate(route)
                    }else
                        nextQuestion(viewModel, questionCount)
                }
            }
        }
    }
}

fun nextQuestion(viewModel: QuizViewModel, questionCount: MutableState<Int>) {
    viewModel.getQuestion()
    questionCount.value += 1
    viewModel.endTimeCounter()
    viewModel.startTimeCounter()
}

@Composable
fun QuestionBody(
    viewModel: QuizViewModel,
    question: Question,
    totalPoints: MutableState<Int>,
    timeQuestion: MutableState<String>,
    finishTime: MutableState<Boolean>,
    onNextClicked: () -> Unit = {},
) {

    val choicesState = remember(question) {
        question.answers.toMutableList()
    }


    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    val isIncorrect = remember(question) {
        mutableStateOf<Boolean>(false)
    }
    val choiceState = remember(question) {
        mutableStateOf<String?>(null)
    }
    val updateAnswer: (String) -> Unit = remember(question) {
        {
            if (choiceState.value.isNullOrEmpty()) {
                correctAnswerState.value = it == question.correct
                choiceState.value = it
                viewModel.endTimeCounter()
                if (correctAnswerState.value == true) {
                    totalPoints.value += timeQuestion.value.toInt()
                    Log.d("TAG", "QuestionBody: ${totalPoints.value} points")
                }
            }

        }
    }

    if (finishTime.value){
        choiceState.value = question.correct
        correctAnswerState.value = true
        finishTime.value = false
    }
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = question.question,
            modifier = Modifier
                .padding(6.dp)
                .align(alignment = Alignment.CenterHorizontally),
            fontSize = 17.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        if (question.imageUrl.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(
                    data = question.imageUrl,
                    builder = {
                        crossfade(true)
                    }),
                contentDescription = "question image",
                modifier = Modifier.size(250.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn() {

            items(choicesState) { choice ->

                Choice(choice,
                    correctAnswerState,
                    choiceState,
                    isIncorrect,
                    question.correct,
                    updateAnswer)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        CustomButton(text = "Siguiente pregunta") {
            if (!choiceState.value.isNullOrEmpty()) {
                onNextClicked()
            }

        }
    }
}

@Composable
fun Choice(
    choice: String,
    correctAnswerState: MutableState<Boolean?>,
    choiceState: MutableState<String?>,
    isIncorrect: MutableState<Boolean>,
    correct: String,
    updateAnswer: (String) -> Unit,
) {
    Row(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth()
        .height(45.dp)
        .border(
            width = 4.dp, brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF371E6F),
                    Color(0xFF371E6F)
                )
            ),
            shape = RoundedCornerShape(15.dp)
        )
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomStartPercent = 50,
                bottomEndPercent = 50
            )
        )
        .clickable {
            updateAnswer(choice)
        }
        .background(
            color = if ((correctAnswerState.value == true && choiceState.value == choice) || (isIncorrect.value && correct == choice)) {
                Color.Green

            } else if (correctAnswerState.value == false && choiceState.value == choice) {
                isIncorrect.value = true
                Color.Red

            } else {
                Color(0xFF110035)
            },
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontWeight = FontWeight.Light,
                color =
                if ((correctAnswerState.value == true && choiceState.value == choice) || (isIncorrect.value && correct == choice)) {
                    Color(0xFF035E12)

                } else if (correctAnswerState.value == false && choiceState.value == choice) {
                    Color(0xFF66030E)
                } else {
                    Color.White
                },
                fontSize = 17.sp
            )) {
                append(choice)
            }
        }
        Text(text = annotatedString)
    }
}





























