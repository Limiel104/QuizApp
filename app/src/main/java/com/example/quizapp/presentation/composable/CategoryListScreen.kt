package com.example.quizapp.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.R
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack

@Composable
fun CategoryListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose category",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h1,
                color = OffBlack
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CategoryCard(
                    categoryImageId = R.drawable.art,
                    label = "Art & Literature"
                )

                CategoryCard(
                    categoryImageId = R.drawable.food,
                    label = "Food & Drink"
                )

                CategoryCard(
                    categoryImageId = R.drawable.geography,
                    label = "Geography"
                )

                CategoryCard(
                    categoryImageId = R.drawable.music,
                    label = "Music"
                )

                CategoryCard(
                    categoryImageId = R.drawable.society,
                    label = "Society & Culture"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CategoryCard(
                    categoryImageId = R.drawable.film,
                    label = "Film & TV"
                )

                CategoryCard(
                    categoryImageId = R.drawable.knowledge,
                    label = "General knowledge"
                )

                CategoryCard(
                    categoryImageId = R.drawable.history,
                    label = "History"
                )

                CategoryCard(
                    categoryImageId = R.drawable.science,
                    label = "Science"
                )

                CategoryCard(
                    categoryImageId = R.drawable.sport,
                    label = "Sport & Leisure"
                )
            }
        }
    }
}