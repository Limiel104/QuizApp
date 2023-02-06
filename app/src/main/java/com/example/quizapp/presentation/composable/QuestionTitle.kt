package com.example.quizapp.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuestionTitle(
    number: String,
    question: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = number,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            color = Color.Gray,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = question,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp, 12.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}