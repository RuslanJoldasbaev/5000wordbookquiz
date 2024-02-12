package com.example.a5000wordsbookquiz.domain

import com.example.a5000wordsbookquiz.data.dao.QuestionDao
import com.example.a5000wordsbookquiz.data.model.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class QuestionRepository(private val dao: QuestionDao) {
    suspend fun getAllQuestion() = flow {
        val list = dao.getAllQuestion()
        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("Pustoy"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}