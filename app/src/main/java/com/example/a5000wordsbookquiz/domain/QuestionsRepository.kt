package com.example.a5000wordsbookquiz.domain

import com.example.a5000wordsbookquiz.data.dao.QuestionsDao
import com.example.a5000wordsbookquiz.data.model.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class QuestionsRepository(private val dao: QuestionsDao) {

    suspend fun getAllQuestions() = flow {
        val list = dao.getQuestions()
        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("List null"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}