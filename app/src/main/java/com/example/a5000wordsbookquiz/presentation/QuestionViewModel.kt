package com.example.a5000wordsbookquiz.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.a5000wordsbookquiz.data.dao.QuestionDao
import com.example.a5000wordsbookquiz.data.model.BookQuizDataBase
import com.example.a5000wordsbookquiz.data.model.QuestionData
import com.example.a5000wordsbookquiz.data.model.ResultData
import com.example.a5000wordsbookquiz.domain.QuestionRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach

class QuestionViewModel(application: Application) : AndroidViewModel(application) {
    private var dao: QuestionDao
    var repo: QuestionRepository

    init {
        dao = BookQuizDataBase.getInstance(application).getAllQuestionDao()
        repo = QuestionRepository(dao)
    }

    val getAllQuestionFlow = MutableSharedFlow<List<QuestionData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun getAllQuestion() {
        repo.getAllQuestion().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllQuestionFlow.emit(it.data)
                }

                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }

                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }
    }
}