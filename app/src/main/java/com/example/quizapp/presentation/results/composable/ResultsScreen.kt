package com.example.quizapp.presentation.results.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.presentation.common.composables.NavigationButton
import com.example.quizapp.presentation.results.ResultsViewModel
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.ui.theme.Red
import com.example.quizapp.util.Screen

@Composable
fun ResultsScreen(
    navController: NavController,
    viewModel: ResultsViewModel = hiltViewModel()
) {
    val correctAnswers = viewModel.resultsState.value.correctAnswers
    val incorrectAnswers = viewModel.resultsState.value.incorrectAnswers
    val time = viewModel.resultsState.value.time
    val rating = viewModel.resultsState.value.resultRating
    val ratingColor = viewModel.resultsState.value.ratingColor
    val accuracy = viewModel.resultsState.value.accuracy
    val category = viewModel.resultsState.value.category

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Results",
            modifier = Modifier
                .fillMaxWidth(),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ResultCard(
                result = "$accuracy%",
                title = "Accuracy",
                resultColor = OffBlack,
                height = 100,
                width = 135,
                fontSize = 26
            )

            Spacer(modifier = Modifier.width(10.dp))

            ResultCard(
                result = time,
                title = "Time",
                resultColor = OffBlack,
                height = 100,
                width = 135,
                fontSize = 26
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ResultCard(
                result = correctAnswers,
                title = "Correct",
                resultColor = Green,
                height = 100,
                width = 135,
                fontSize = 26
            )

            Spacer(modifier = Modifier.width(10.dp))

            ResultCard(
                result = incorrectAnswers,
                title = "Incorrect",
                resultColor = Red,
                height = 100,
                width = 135,
                fontSize = 26
            )
        }

        ResultCard(
            result = rating,
            title = "Score",
            resultColor = ratingColor,
            height = 100,
            width = 280,
            fontSize = 34
        )

        Column() {
            NavigationButton(
                text = "See best scores for this category",
                onClick = {
                    navController.navigate(
                        Screen.StatsScreen.route
                                + "category=$category"
                    )
                }
            )

            NavigationButton(
                text = "Go Back",
                onClick = {
                    navController.popBackStack(Screen.SelectCategoryScreen.route, false)
                }
            )
        }
    }
}