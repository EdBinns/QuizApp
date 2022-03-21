package com.edbinns.quizapp.navigation

enum class QuizScreens {
    MainScreen,
    QuizScreen,
    ResultScreen,
    ScoreScreen;

    companion object {
        fun fromRoute(route: String): QuizScreens = when (route.substringBefore("/")) {
            MainScreen.name -> MainScreen
            QuizScreen.name -> QuizScreen
            ResultScreen.name -> ResultScreen
            ScoreScreen.name -> ScoreScreen
            else -> throw IllegalArgumentException("Route $route")
        }
    }
}