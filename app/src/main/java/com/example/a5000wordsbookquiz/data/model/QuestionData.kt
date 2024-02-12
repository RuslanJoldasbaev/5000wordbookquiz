package com.example.a5000wordsbookquiz.data.model

import androidx.room.Entity

@Entity(tableName = "question")
data class QuestionData(
    val id: Int,
    val question: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val correctAnswer: String
)
