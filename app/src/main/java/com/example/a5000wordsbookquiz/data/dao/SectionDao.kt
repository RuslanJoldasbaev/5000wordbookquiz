package com.example.a5000wordsbookquiz.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.data.SectionData
import com.example.a5000wordsbookquiz.data.model.data.TopicData


@Dao()
interface SectionDao {

    @Query("SELECT * FROM section WHERE topic_id=:topicId")
    suspend fun getSection(topicId: Int): MutableList<SectionData>
}