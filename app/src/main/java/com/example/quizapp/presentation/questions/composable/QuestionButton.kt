package com.example.quizapp.presentation.questions.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.OffBlack

@Composable
fun QuestionButton(
    text: String,
    color: Color,
    onClick: (String) -> Unit
) {
    if(color != OffBlack) {
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 25.dp,
                    vertical = 9.dp
                ),
            border = BorderStroke(1.dp, color),
            shape = RoundedCornerShape(35.dp),
            onClick = { onClick(text) }
        ) {
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier
                    .padding(7.dp)
            )
        }
    }
    else {
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 25.dp,
                    vertical = 9.dp
                ),
            border = BorderStroke(1.dp, color),
            shape = RoundedCornerShape(35.dp),
            onClick = { onClick(text) }
        ) {
            Text(
                text = text,
                color = color,
                modifier = Modifier
                    .padding(7.dp)
            )
        }
    }
}