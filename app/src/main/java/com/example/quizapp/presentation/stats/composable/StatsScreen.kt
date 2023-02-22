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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack

@Composable
fun StatsScreen(
    navController: NavController
) {
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
                text = "Best 25 Scores",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h1,
                color = OffBlack
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(
                    items = listOf(
                        "20-02-2023  15  1:23  Hard",
                        "20-02-2023  13  5:00  Easy",
                        "15-03-2023  18  0:56  Hard",
                        "22-05-2022  12  3:02  Medium"
                    )
                ) { index, item ->
                    StatsListItem(
                        item = item,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}