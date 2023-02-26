package com.example.quizapp.presentation.select_category.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.ui.theme.OffBlack
import com.example.quizapp.util.Constants.arts_category
import com.example.quizapp.util.Constants.film_category
import com.example.quizapp.util.Constants.food_category
import com.example.quizapp.util.Constants.geography_category
import com.example.quizapp.util.Constants.history_category
import com.example.quizapp.util.Constants.knowledge_category
import com.example.quizapp.util.Constants.music_category
import com.example.quizapp.util.Constants.science_category
import com.example.quizapp.util.Constants.society_category
import com.example.quizapp.util.Constants.sport_category
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
                text = stringResource(id = R.string.choose_category),
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
                    categoryImageId = R.drawable.arts,
                    label = stringResource(id = R.string.arts_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$arts_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.food,
                    label = stringResource(id = R.string.food_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$food_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.geography,
                    label = stringResource(id = R.string.geography_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$geography_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.music,
                    label = stringResource(id = R.string.music_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$music_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.society,
                    label = stringResource(id = R.string.society_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$society_category"
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
                    label = stringResource(id = R.string.film_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$film_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.knowledge,
                    label = stringResource(id = R.string.knowledge_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$knowledge_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.history,
                    label = stringResource(id = R.string.history_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$history_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.science,
                    label = stringResource(id = R.string.science_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$science_category"
                        )
                    }
                )

                CategoryCard(
                    categoryImageId = R.drawable.sport,
                    label = stringResource(id = R.string.sport_label),
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Screen.SelectDifficultyScreen.route
                                    + "category=$sport_category"
                        )
                    }
                )
            }
        }
    }
}