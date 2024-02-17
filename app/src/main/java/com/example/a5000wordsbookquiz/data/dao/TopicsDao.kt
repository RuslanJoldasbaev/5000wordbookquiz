package com.example.a5000wordsbookquiz.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.data.TopicData


@Dao()
interface TopicsDao {

    @Query("SELECT * FROM topic")
    suspend fun getTopics(): List<TopicData>
}