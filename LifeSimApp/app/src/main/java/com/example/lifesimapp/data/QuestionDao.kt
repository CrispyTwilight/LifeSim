// Auth: John O'Neal
// Date: 05/09/2024
// Desc: This interface is used to access the database with read/write access

package com.example.lifesimapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM QuestionEntity")
    fun getAllQuestions(): List<QuestionEntity>

    @Query("SELECT * FROM OptionEntity WHERE questionId = :questionId")
    fun getOptionsForQuestion(questionId: Int): List<OptionEntity>

    // TODO: This is for debugging
    @Query("SELECT COUNT(*) FROM QuestionEntity")
    suspend fun getQuestionCount(): Int

    @Insert
    fun insert(question: QuestionEntity): Long

    @Insert
    fun insert(option: OptionEntity): Long
}