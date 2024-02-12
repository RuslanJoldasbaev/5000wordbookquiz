package com.example.a5000wordsbookquiz.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a5000wordsbookquiz.data.dao.QuestionDao

@Database(entities = [QuestionData::class], version = 1)
abstract class BookQuizDataBase : RoomDatabase() {
    companion object {
        private var INSTANCE: BookQuizDataBase? = null

        fun getInstance(context: Context): BookQuizDataBase {
            INSTANCE?.let {
                return it
            }

            val db = Room.databaseBuilder(
                context, BookQuizDataBase::class.java, "quiz_5000_word.db"
            )
                .createFromAsset("quiz_5000_word.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

            INSTANCE = db
            return db
        }
    }

    abstract fun getAllQuestionDao(): QuestionDao
}