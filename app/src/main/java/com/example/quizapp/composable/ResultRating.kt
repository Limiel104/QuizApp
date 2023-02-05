package com.example.quizapp.composable

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
fun ResultRating(
    rating: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .padding(5.dp)
                    .size(170.dp)
            ) {
                drawCircle(
                    color = color,
                    radius = 150f,
                    style = Stroke(
                        width = 7f,
                        cap = StrokeCap.Round
                    )
                )

                drawCircle(
                    color = color,
                    radius = 140f,
                    style = Stroke(
                        width = 5f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = rating,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        }
    }
}