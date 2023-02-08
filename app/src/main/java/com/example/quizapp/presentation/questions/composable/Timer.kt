package com.example.quizapp.presentation.questions.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Timer(
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .padding(5.dp)
                    .size(70.dp)
            ) {
                drawCircle(
                    color = Color.LightGray,
                    radius = 50f,
                    style = Stroke(
                        width = 4f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = time,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}