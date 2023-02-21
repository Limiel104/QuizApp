package com.example.quizapp.data.repository

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER

class FakeQuizRepository : QuizRepository {

    private val questionEntitiesList = mutableListOf<QuestionEntity>()

    override suspend fun insertQuestions(questionEntities: List<QuestionEntity>) {
        for (question in questionEntities) {
            questionEntitiesList.add(question)
        }
    }

    override suspend fun getQuestionsFromApi(category: String, difficulty: String): List<Question> {
        val questionsFromApi = mutableListOf<Question>()

        for(i in 0..5) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = "Geography",
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = "Easy",
                )
            )
        }

        for(i in 6..10) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = "Society & Culture",
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = "Easy",
                )
            )
        }

        for(i in 11..15) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = "Society & Culture",
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = "Hard",
                )
            )
        }

        for(i in 16..20) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = "Geography",
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = "Medium",
                )
            )
        }

        questionsFromApi.shuffle()

        val responseFromApi = mutableListOf<Question>()

        for(question in questionsFromApi){
            if(responseFromApi.size < QUESTIONS_NUMBER) {
                if(question.category == category && question.difficulty == difficulty) {
                    responseFromApi.add(question)
                }
            }
        }

        return responseFromApi
    }

    override suspend fun getQuestionsFromDatabase(
        category: String,
        difficulty: String
    ): List<QuestionEntity> {
        val questionsFromDb = mutableListOf<QuestionEntity>()

        for(question in questionEntitiesList){
            if(questionsFromDb.size < QUESTIONS_NUMBER) {
                if(question.category == category && question.difficulty == difficulty) {
                    questionsFromDb.add(question)
                }
            }
        }

        return questionsFromDb
    }
}