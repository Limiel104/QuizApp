package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.presentation.composable.ResultsScreen
import com.example.quizapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    CategoryListScreen()
//                    ChooseDifficultyScreen(
//                        categoryImageId = R.drawable.food_big,
//                        label = "Food & Drinks"
//                    )
//                    QuestionsScreen()
                    ResultsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizAppTheme {
//        CategoryListScreen()
//        ChooseDifficultyScreen(
//            categoryImageId = R.drawable.food_big,
//            label = "Food & Drinks"
//        )
//        QuestionsScreen()
        ResultsScreen()
    }
}