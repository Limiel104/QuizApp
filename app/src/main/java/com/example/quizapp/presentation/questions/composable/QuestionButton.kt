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
    isButtonLocked: Boolean,
    onClick: (String) -> Unit
) {
    OutlinedButton(
        colors = if (color != OffBlack) ButtonDefaults.buttonColors(color) else ButtonDefaults.buttonColors(
            Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 25.dp,
                vertical = 9.dp
            ),
        border = BorderStroke(1.dp, color),
        shape = RoundedCornerShape(35.dp),
        onClick = {
            if (!isButtonLocked) {
                onClick(text)
            }
        }
    ) {
        Text(
            text = text,
            color = if (color != OffBlack) Color.White else color,
            modifier = Modifier
                .padding(7.dp)
        )
    }
}