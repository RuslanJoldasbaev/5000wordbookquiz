package com.example.a5000wordsbookquiz.domain

import com.example.a5000wordsbookquiz.data.dao.SectionDao
import com.example.a5000wordsbookquiz.data.dao.TopicsDao
import com.example.a5000wordsbookquiz.data.model.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SectionRepository(private val dao: SectionDao) {

    suspend fun getAllSection(topicId: Int) = flow {
        val list = dao.getSection(topicId)
        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("List null"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}