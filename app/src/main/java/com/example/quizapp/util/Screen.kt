package com.example.quizapp.util

sealed class Screen(val route: String) {
    object CategoryListScreen: Screen("category_list_screen")
}