package com.example.quizapp.presentation.stats.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack

@Composable
fun ChipItem(
    text: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: (String) -> Unit
) {
    Surface(
        modifier = modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = CircleShape,
        color = if (isSelected) Green else OffBlack,
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onClick(text)
                    }
                )
        ) {
            Text(
                text = text,
                color = if (isSelected) Color.White else LightGray3,
                modifier = Modifier.padding(7.dp,4.dp)
            )
        }
    }
}