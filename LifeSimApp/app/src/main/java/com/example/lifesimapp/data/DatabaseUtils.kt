// Auth: John O'Neal
// Date: 05/09/2024
// Desc: Utility functions for database operations

package com.example.lifesimapp.data

import android.content.Context
import android.util.Log
import com.example.lifesimapp.classes.Option
import com.example.lifesimapp.classes.Question
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getQuestions(context: Context): List<Question> = withContext(Dispatchers.IO) {
    val db = AppDatabase.getDatabase(context)
    return@withContext db.questionDao().getAllQuestions().map { questionEntity ->
        val options =
            db.questionDao().getOptionsForQuestion(questionEntity.id).map { optionEntity ->
                Option(optionEntity.option, optionEntity.wealthChange, optionEntity.happinessChange)
            }
        Question(questionEntity.title, questionEntity.description, options)
    }
}

suspend fun populateDatabase(context: Context) {
    Log.d("DatabaseUtils", "populateDatabase executing from DatabaseUtils")
    val db = AppDatabase.getDatabase(context)
    val questionDao = db.questionDao()

    // Read the JSON file from the assets directory
    val json = context.assets.open("questions.json").bufferedReader().use { it.readText() }

    // Parse the JSON file into a list of Question objects
    val questions = Gson().fromJson(json, Array<Question>::class.java).toList()
    Log.d("DatabaseUtils", "questions: $questions")

    // Convert the Question objects to QuestionEntity and OptionEntity objects and insert them into the database
    questions.forEach { question ->
        val questionEntity = QuestionEntity(0, question.title, question.description)
        val questionId = questionDao.insert(questionEntity).toInt()

        question.options.forEach { option ->
            val optionEntity =
                OptionEntity(0, questionId, option.text, option.wealth, option.happiness)
            questionDao.insert(optionEntity)
        }
        Log.d("DatabaseUtils", "questionId: $questionId added to database")
    }
}