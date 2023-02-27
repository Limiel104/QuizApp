package com.example.quizapp.presentation.questions.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.presentation.questions.QuestionsEvent
import com.example.quizapp.presentation.questions.QuestionsViewModel
import com.example.quizapp.ui.theme.LightGray3
import com.example.quizapp.util.Screen
import kotlinx.coroutines.delay

@Composable
fun QuestionsScreen(
    navController: NavController,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    val questionState = viewModel.displayedQuestionState.value.question
    val answersState = viewModel.displayedQuestionState.value.answers
    val answersColorState = viewModel.displayedQuestionState.value.answersColors
    val counterState = viewModel.displayedQuestionState.value.counter
    val time = viewModel.timerState.value.displayedTime
    val currentTime = viewModel.timerState.value.currentTimeInSeconds
    val isTimerRunning = viewModel.timerState.value.isTimerRunning
    val result = viewModel.displayedQuestionState.value.result
    val isButtonLocked = viewModel.displayedQuestionState.value.isButtonLocked
    val category = viewModel.questionListState.value.category
    val isDialogActivated = viewModel.dialogState.value.isDialogActivated
    val isDialogDismissed = viewModel.dialogState.value.isDialogDismissed

    LaunchedEffect(key1 = isTimerRunning, key2 = currentTime) {
        if(isTimerRunning) {
            delay(1000L)
            viewModel.onEvent(QuestionsEvent.TimeDisplayedChange(currentTime))
        }
        else if(!isDialogActivated) {
            navController.navigate(
                Screen.ResultsScreen.route
                        + "result=$result"
                        + "&time=$currentTime"
                        + "&category=$category"
            )
        }
    }

    LaunchedEffect(key1 = isDialogDismissed) {
        if(isDialogActivated) {
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray3),
    ) {
        Timer(
            time = time
        )

        if(isDialogActivated) {
            NoQuestionsDialog(
                onDismiss = {
                    viewModel.onEvent(QuestionsEvent.DialogDismissed)
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            QuestionTitle(
                number = stringResource(id = R.string.question) + counterState,
                question = questionState
            )

            Column() {
                QuestionButton(
                    text = answersState[0],
                    color = answersColorState[0],
                    isButtonLocked = isButtonLocked,
                    onClick = {
                        viewModel.onEvent(QuestionsEvent.SelectedAnswer(answersState[0]))
                    }
                )

                QuestionButton(
                    text = answersState[1],
                    color = answersColorState[1],
                    isButtonLocked = isButtonLocked,
                    onClick = {
                        viewModel.onEvent(QuestionsEvent.SelectedAnswer(answersState[1]))
                    }
                )

                QuestionButton(
                    text = answersState[2],
                    color = answersColorState[2],
                    isButtonLocked = isButtonLocked,
                    onClick = {
                        viewModel.onEvent(QuestionsEvent.SelectedAnswer(answersState[2]))
                    }
                )

                QuestionButton(
                    text = answersState[3],
                    color = answersColorState[3],
                    isButtonLocked = isButtonLocked,
                    onClick = {
                        viewModel.onEvent(QuestionsEvent.SelectedAnswer(answersState[3]))
                    }
                )
            }
        }
    }
}