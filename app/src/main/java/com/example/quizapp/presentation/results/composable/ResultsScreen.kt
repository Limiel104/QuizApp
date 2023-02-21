package com.example.quizapp.presentation.results.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Result",
            modifier = Modifier
                .fillMaxWidth(),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Text(
            text = "Accuracy: $accuracy%",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

        Text(
            text = "Time: $time",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ResultCard(
                result = correctAnswers,
                title = "Correct",
                resultColor = Green
            )

            ResultCard(
                result = incorrectAnswers,
                title = "Incorrect",
                resultColor = Red
            )
        }

        ResultRating(
            rating = rating,
            color = ratingColor
        )

        OutlinedButton(
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 25.dp,
                    vertical = 9.dp
                ),
            border = BorderStroke(1.dp, OffBlack),
            shape = RoundedCornerShape(35.dp),
            onClick = {
                navController.popBackStack(Screen.SelectCategoryScreen.route,false)
            }
        ) {
            Text(
                text = "Go Back",
                color = OffBlack,
                modifier = Modifier
                    .padding(7.dp)
            )
        }
    }
}