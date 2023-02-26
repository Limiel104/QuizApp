package com.example.quizapp.presentation.stats.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizapp.R
import com.example.quizapp.presentation.stats.StatsEvent
import com.example.quizapp.presentation.stats.StatsViewModel
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.util.Constants.easy_difficulty
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.hard_difficulty
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.medium_difficulty
import com.example.quizapp.util.Constants.medium_label

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = hiltViewModel()
) {
    val statsList = viewModel.statsListState.value.statsList
    val query = viewModel.statsListState.value.query

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.best_scores),
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h1,
                color = OffBlack
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChipItem(
                text = easy_label,
                isSelected = query == easy_difficulty,
                onClick = {
                    viewModel.onEvent(StatsEvent.OnChipToggled(easy_difficulty))
                }
            )

            ChipItem(
                text = medium_label,
                isSelected = query == medium_difficulty,
                onClick = {
                    viewModel.onEvent(StatsEvent.OnChipToggled(medium_difficulty))
                }
            )

            ChipItem(
                text = hard_label,
                isSelected = query == hard_difficulty,
                onClick = {
                    viewModel.onEvent(StatsEvent.OnChipToggled(hard_difficulty))
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    items = statsList
                ) { index, item ->
                    StatsListItem(
                        score = item.score.toString(),
                        time = viewModel.setTime(item.time),
                        difficulty = item.difficulty,
                        date = viewModel.setDate(item.date),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}