// Auth: John O'Neal
// Date: 05/09/2024
// Desc: These classes store data for the questions and options

package com.example.lifesimapp.classes

data class Question(
    val title: String,
    val description: String,
    val options: List<Option>
)

data class Option(
    val text: String,
    val wealth: Int,
    val happiness: Int
)