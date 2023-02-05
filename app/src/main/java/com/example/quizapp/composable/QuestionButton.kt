package com.example.quizapp.composable

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

@Composable
fun QuestionButton(
    text: String,
    color: Color
) {
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
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = text,
            color = color,
            modifier = Modifier
                .padding(7.dp)
        )
    }
}