package com.example.quizapp.presentation.stats.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.quizapp.ui.theme.LightGreen

@Composable
fun StatsListItem(
    score: String,
    time: String,
    difficulty: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Center
    ) {
        StatItem(
            text = date,
            height = 50,
            width = 110,
            color = Color.White
        )

        StatItem(
            text = score,
            height = 50,
            width = 50,
            color = LightGreen
        )

        StatItem(
            text = time,
            height = 50,
            width = 65,
            color = Color.White
        )

        StatItem(
            text = difficulty,
            height = 50,
            width = 90,
            color = LightGreen
        )
    }
}