package com.example.quizapp.presentation.select_difficulty.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.presentation.select_difficulty.SelectDifficultyViewModel
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.util.Screen

@Composable
fun SelectDifficultyScreen(
    navController: NavController,
    viewModel: SelectDifficultyViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = viewModel.state.value.label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painterResource(
                id = viewModel.state.value.iconId
            ),
            contentDescription = viewModel.state.value.label
        )

        Spacer(modifier = Modifier.height(5.dp))

        Column() {
            Text(
                text = "Choose difficulty level",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                color = Color.Gray,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            DifficultyButton(
                text = "Easy",
                onClick = {
                    navController.navigate(
                        Screen.QuestionsScreen.route
                                + "category=${viewModel.state.value.selectedCategory}"
                                + "&difficulty=easy"
                    )
                }
            )

            DifficultyButton(
                text = "Medium",
                onClick = {
                    navController.navigate(
                        Screen.QuestionsScreen.route
                                + "category=${viewModel.state.value.selectedCategory}"
                                + "&difficulty=medium"
                    )
                }
            )

            DifficultyButton(
                text = "Hard",
                onClick = {
                    navController.navigate(
                        Screen.QuestionsScreen.route
                                + "category=${viewModel.state.value.selectedCategory}"
                                + "&difficulty=hard"
                    )
                }
            )
        }
    }
}