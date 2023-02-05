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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
//                    CategoryListScreen()
//                    ChooseDifficultyScreen(
//                        categoryImageId = R.drawable.food_big,
//                        label = "Food & Drinks"
//                    )
//                    QuestionsScreen()
                    ResultsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizAppTheme {
//        CategoryListScreen()
//        ChooseDifficultyScreen(
//            categoryImageId = R.drawable.food_big,
//            label = "Food & Drinks"
//        )
//        QuestionsScreen()
        ResultsScreen()
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            color = OffBlack,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painterResource(
                id = categoryImageId
            ),
            contentDescription = label
        )

        Spacer(modifier = Modifier.height(5.dp))

        Column() {
            Text(
                text = "Choose difficulty level",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                color = Color.Gray,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            DifficultyButton(text = "Easy")
            DifficultyButton(text = "Medium")
            DifficultyButton(text = "Hard")
        }
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
                vertical = 9.dp
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

@Composable
fun QuestionsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
    ) {
        Timer(
            time = "4:37"
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            QuestionTitle(
                number = "Question 1",
                question = "What Word Is Used To Describe Bell Shaped Flowers?"
            )

            Column() {
                QuestionButton(
                    text = "Campanulate",
                    color = Green
                )

                QuestionButton(
                    text = "Clochical",
                    color = OffBlack
                )

                QuestionButton(
                    text = "Cambarn",
                    color = Red
                )

                QuestionButton(
                    text = "Belieux",
                    color = OffBlack
                )
            }
        }
    }
}

@Composable
fun Timer(
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .padding(5.dp)
                    .size(70.dp)
            ) {
                drawCircle(
                    color = Color.LightGray,
                    radius = 50f,
                    style = Stroke(
                        width = 4f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = time,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

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

@Composable
fun QuestionTitle(
    number: String,
    question: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = number,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            color = Color.Gray,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = question,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp, 12.dp),
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

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

@Composable
fun ResultCard(
    result: String,
    title: String,
    resultColor: Color
) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = result,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = resultColor
            )

            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                color = resultColor
            )
        }
    }
}

@Composable
fun ResultRating(
    rating: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .padding(5.dp)
                    .size(170.dp)
            ) {
                drawCircle(
                    color = color,
                    radius = 150f,
                    style = Stroke(
                        width = 7f,
                        cap = StrokeCap.Round
                    )
                )

                drawCircle(
                    color = color,
                    radius = 140f,
                    style = Stroke(
                        width = 5f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = rating,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        }
    }
}