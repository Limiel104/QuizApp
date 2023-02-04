package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //CategoryListScreen()
                    ChooseDifficultyScreen(
                        categoryImageId = R.drawable.food_big,
                        label = "Food & Drinks"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizAppTheme {
        //CategoryListScreen()
        ChooseDifficultyScreen(
            categoryImageId = R.drawable.food_big,
            label = "Food & Drinks"
        )
    }
}

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

@Composable
fun CategoryCard(
    categoryImageId: Int,
    label: String
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(90.dp)
            .background(Color.White)
            .clickable { }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(
                    id = categoryImageId
                ),
                contentDescription = label
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun ChooseDifficultyScreen(
    categoryImageId: Int,
    label: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(
                id = categoryImageId
            ),
            contentDescription = label
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Choose difficulty level",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            color = Color.Gray,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(5.dp))

        DifficultyButton(text = "Easy")
        DifficultyButton(text = "Medium")
        DifficultyButton(text = "Hard")
    }
}

@Composable
fun DifficultyButton(
    text: String
) {
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 25.dp,
                vertical = 5.dp
            ),
        border = BorderStroke(1.dp, OffBlack),
        shape = RoundedCornerShape(35.dp),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = text,
            color = OffBlack,
            modifier = Modifier
                .padding(7.dp)
        )
    }
}