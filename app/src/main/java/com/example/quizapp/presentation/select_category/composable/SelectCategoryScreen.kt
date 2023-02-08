package com.example.quizapp.presentation.select_category.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.util.Screen

@Composable
fun SelectCategoryScreen(
    navController: NavController,
) {
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
                    label = "Art & Literature",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=art_and_literature"
                                    + "&label=Art & Literature"
                                    + "&iconId=${R.drawable.art_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.food,
                    label = "Food & Drink",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=food_and_drink"
                                    + "&label=Food & Drink"
                                    + "&iconId=${R.drawable.food_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.geography,
                    label = "Geography",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=geography"
                                    + "&label=Geography"
                                    + "&iconId=${R.drawable.geography_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.music,
                    label = "Music",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=music"
                                    + "&label=Music"
                                    + "&iconId=${R.drawable.music_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.society,
                    label = "Society & Culture",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=society_and_culture"
                                    + "&label=Society & Culture"
                                    + "&iconId=${R.drawable.society_big}"
                        )
                    }
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
                    label = "Film & TV",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=film_and_tv"
                                    + "&label=Film & TV"
                                    + "&iconId=${R.drawable.film_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.knowledge,
                    label = "General knowledge",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=general_knowledge"
                                    + "&label=General knowledge"
                                    + "&iconId=${R.drawable.knowledge_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.history,
                    label = "History",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=history"
                                    + "&label=History"
                                    + "&iconId=${R.drawable.history_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.science,
                    label = "Science",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=science"
                                    + "&label=Science"
                                    + "&iconId=${R.drawable.science_big}"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.sport,
                    label = "Sport & Leisure",
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=sport_and_leisure"
                                    + "&label=Sport & Leisure"
                                    + "&iconId=${R.drawable.sport_big}"
                        )
                    }
                )
            }
        }
    }
}