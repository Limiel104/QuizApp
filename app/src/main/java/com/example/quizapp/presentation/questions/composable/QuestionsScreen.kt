package com.example.quizapp.presentation.questions.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.presentation.questions.QuestionsViewModel
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.ui.theme.Red

@Composable
fun QuestionsScreen(
    navController: NavController,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
    ) {
        Timer(
            time = "4:37"
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            QuestionTitle(
                number = "Question 1",
                question = "What Word Is Used To Describe Bell Shaped Flowers?"
            )

            Column() {
                QuestionButton(
                    text = "Campanulate",
                    color = Green
                )

                QuestionButton(
                    text = "Clochical",
                    color = OffBlack
                )

                QuestionButton(
                    text = "Cambarn",
                    color = Red
                )

                QuestionButton(
                    text = "Belieux",
                    color = OffBlack
                )
            }
        }
    }
}