package com.example.a5000wordsbookquiz.domain

import com.example.a5000wordsbookquiz.data.dao.TopicsDao
import com.example.a5000wordsbookquiz.data.model.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class TopicsRepository(private val dao: TopicsDao) {

    suspend fun getAllTopics() = flow {
        val list = dao.getTopics()
        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("List null"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}