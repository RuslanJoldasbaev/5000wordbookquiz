package com.example.a5000wordsbookquiz.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5000wordsbookquiz.data.dao.QuestionsDao
import com.example.a5000wordsbookquiz.data.model.db.QuizDatabase
import com.example.a5000wordsbookquiz.data.model.ResultData
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.domain.QuestionsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class QuestionsViewModel(application: Application) : AndroidViewModel(application) {
    private var dao: QuestionsDao
    var repo: QuestionsRepository

    init {
        dao = QuizDatabase.getInstance(application).getQuestionsDao()
        repo = QuestionsRepository(dao)
    }

    val getAllQuestionsFlow = MutableSharedFlow<List<QuestionData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun getAllQuestions() {
        repo.getAllQuestions().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllQuestionsFlow.emit(it.data)
                }

                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }

                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}