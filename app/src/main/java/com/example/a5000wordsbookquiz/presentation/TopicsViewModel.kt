package com.example.a5000wordsbookquiz.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.a5000wordsbookquiz.data.dao.QuestionsDao
import com.example.a5000wordsbookquiz.data.dao.TopicsDao
import com.example.a5000wordsbookquiz.data.model.data.QuestionData
import com.example.a5000wordsbookquiz.data.model.db.QuizDatabase
import com.example.a5000wordsbookquiz.data.model.ResultData
import com.example.a5000wordsbookquiz.data.model.data.TopicData
import com.example.a5000wordsbookquiz.domain.QuestionsRepository
import com.example.a5000wordsbookquiz.domain.TopicsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TopicsViewModel(application: Application) : AndroidViewModel(application) {
    private var dao: TopicsDao
    var repo: TopicsRepository

    init {
        dao = QuizDatabase.getInstance(application).getTopicsDao()
        repo = TopicsRepository(dao)
    }

    val getAllTopicFlow = MutableSharedFlow<List<TopicData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun getAllTopics() {
        repo.getAllTopics().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllTopicFlow.emit(it.data)
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