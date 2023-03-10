package com.example.quizapp.presentation.common.composables

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
fun NavigationButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 25.dp,
                vertical = 9.dp
            ),
        border = BorderStroke(1.dp, OffBlack),
        shape = RoundedCornerShape(35.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = OffBlack,
            modifier = Modifier
                .padding(7.dp)
        )
    }
}