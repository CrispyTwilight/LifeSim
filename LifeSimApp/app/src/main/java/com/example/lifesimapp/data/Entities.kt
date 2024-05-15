// Auth: John O'Neal
// Date: 05/09/2024
// Desc: Define the entities for the database (tables)

package com.example.lifesimapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String
)

@Entity
data class OptionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val questionId: Int, // Foreign key to QuestionEntity
    val option: String,
    val wealthChange: Int,
    val happinessChange: Int
)