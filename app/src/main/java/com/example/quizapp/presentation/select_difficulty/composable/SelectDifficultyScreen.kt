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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.presentation.common.composables.NavigationButton
import com.example.quizapp.presentation.select_difficulty.SelectDifficultyViewModel
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.util.Constants.easy_difficulty
import com.example.quizapp.util.Constants.hard_difficulty
import com.example.quizapp.util.Constants.medium_difficulty
import com.example.quizapp.util.Screen

@Composable
fun SelectDifficultyScreen(
    navController: NavController,
    viewModel: SelectDifficultyViewModel = hiltViewModel()
) {
    val iconId = viewModel.state.value.iconId
    val label = viewModel.state.value.label
    val category = viewModel.state.value.category

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = label,
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
                id = iconId
            ),
            contentDescription = stringResource(id = R.string.icon)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Column() {
            Text(
                text = stringResource(id = R.string.choose_difficulty_level),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                color = Color.Gray,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            NavigationButton(
                text = stringResource(id = R.string.easy_label),
                onClick = {
                    navController.navigate(
                        Screen.QuestionsScreen.route
                                + "category=$category"
                                + "&difficulty=$easy_difficulty"
                    )
                }
            )

            NavigationButton(
                text = stringResource(id = R.string.medium_label),
                onClick = {
                    navController.navigate(
                        Screen.QuestionsScreen.route
                                + "category=$category"
                                + "&difficulty=$medium_difficulty"
                    )
                }
            )

            NavigationButton(
                text = stringResource(id = R.string.hard_label),
                onClick = {
                    navController.navigate(
                        Screen.QuestionsScreen.route
                                + "category=$category"
                                + "&difficulty=$hard_difficulty"
                    )
                }
            )
        }
    }
}