package com.example.a5000wordsbookquiz.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a5000wordsbookquiz.data.dao.QuestionsDao
import com.example.a5000wordsbookquiz.data.dao.SectionDao
import com.example.a5000wordsbookquiz.data.dao.TopicsDao
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.data.SectionData
import com.example.a5000wordsbookquiz.data.model.data.TopicData

@Database(entities = [TopicData::class, QuestionData::class, SectionData::class], version = 4)
abstract class QuizDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            INSTANCE?.let {
                return it
            }

            val db = Room.databaseBuilder(
                context, QuizDatabase::class.java, "quiz_5000_word.db"
            )
                .createFromAsset("quiz_5000_word.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

            INSTANCE = db
            return db
        }
    }
    abstract fun getSectionDao(): SectionDao
    abstract fun getTopicsDao(): TopicsDao
    abstract fun getQuestionsDao(): QuestionsDao
}