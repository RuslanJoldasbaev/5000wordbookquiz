package com.example.a5000wordsbookquiz.data.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class SectionData(
    @PrimaryKey val id: Int,
    val name: String,
    val topic_id: Int
)
