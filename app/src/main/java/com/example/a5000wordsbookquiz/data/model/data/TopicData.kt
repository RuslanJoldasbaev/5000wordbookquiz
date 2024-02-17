package com.example.a5000wordsbookquiz.data.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic")
data class TopicData(
    @PrimaryKey val id: Int,
    val name: String
)
