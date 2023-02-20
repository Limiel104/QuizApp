package com.example.quizapp.util

sealed class Screen(val route: String) {
    object SelectCategoryScreen: Screen("select_category_screen")
    object SelectDifficultyScreen: Screen("select_difficulty_screen")
    object QuestionsScreen: Screen("questions_screen")
    object ResultsScreen: Screen("results_screen")
}