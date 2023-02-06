package com.example.quizapp.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.Green
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.ui.theme.Red

@Composable
fun ResultsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Result",
            modifier = Modifier
                .fillMaxWidth(),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Text(
            text = "Accuracy: 89,9%",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ResultCard(
                result = "16",
                title = "Correct",
                resultColor = Green
            )

            ResultCard(
                result = "4",
                title = "Incorrect",
                resultColor = Red
            )
        }

        ResultRating(
            rating = "Very Good",
            color = Green
        )

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
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Go Back",
                color = OffBlack,
                modifier = Modifier
                    .padding(7.dp)
            )
        }
    }
}