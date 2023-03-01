package com.example.quizapp.data.repository

import com.example.quizapp.data.local.QuestionEntity
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuestionRepository
import com.example.quizapp.util.Constants.QUESTIONS_NUMBER
import com.example.quizapp.util.Constants.easy_label
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.hard_label
import com.example.quizapp.util.Constants.medium_label
import com.example.quizapp.util.Constants.society_label

class FakeQuestionRepository: QuestionRepository {

    private val questionEntitiesList = mutableListOf<QuestionEntity>()

    override suspend fun insertQuestions(questionEntities: List<QuestionEntity>) {
        for (question in questionEntities) {
            questionEntitiesList.add(question)
        }
    }

    override suspend fun getQuestionsFromApi(category: String, difficulty: String): List<Question> {
        val questionsFromApi = mutableListOf<Question>()

        for(i in 0..QUESTIONS_NUMBER) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = geography_label,
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = easy_label
                )
            )
        }

        for(i in QUESTIONS_NUMBER+1..2*QUESTIONS_NUMBER) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = society_label,
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = easy_label,
                )
            )
        }

        for(i in 2*QUESTIONS_NUMBER+1..3*QUESTIONS_NUMBER) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = society_label,
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = hard_label,
                )
            )
        }

        for(i in 3*QUESTIONS_NUMBER+1..4*QUESTIONS_NUMBER) {
            questionsFromApi.add(
                Question(
                    id = i.toString(),
                    category = geography_label,
                    correctAnswer = "Correct",
                    incorrectAnswersList = listOf("Incorrect1","Incorrect2","Incorrect3"),
                    question = "Question $i",
                    difficulty = medium_label,
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