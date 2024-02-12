package com.example.a5000wordsbookquiz.data.dao

import androidx.room.Query
import com.example.a5000wordsbookquiz.data.model.QuestionData

interface QuestionDao {
    @Query("SELECT * FROM question")
    suspend fun getAllQuestion() : List<QuestionData>
}