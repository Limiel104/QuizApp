package com.example.quizapp.presentation.stats.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun StatsListItem(
    item: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.White),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = item
        )
    }
}