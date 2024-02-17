package com.example.a5000wordsbookquiz.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.data.TopicData


@Dao()
interface QuestionsDao {

    @Query("SELECT * FROM question")
    suspend fun getQuestions(): List<QuestionData>
}