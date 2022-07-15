package com.angelo.cleanarch.business.domain.interactors

import com.angelo.cleanarch.business.data.cache.TodoCacheDataSource
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTodo constructor(
    private val cacheDataSource: TodoCacheDataSource
) {

    suspend fun execute(id: Int): Flow<DataState<Todo>> = flow {
        val cachedTodo = cacheDataSource.getTodo(id)
        emit(DataState.Success(cachedTodo))
    }

}