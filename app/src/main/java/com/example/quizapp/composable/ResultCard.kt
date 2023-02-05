package com.example.quizapp.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultCard(
    result: String,
    title: String,
    resultColor: Color
) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = result,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = resultColor
            )

            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                color = resultColor
            )
        }
    }
}