package com.angelo.cleanarch.business.domain.interactors

import com.angelo.cleanarch.business.data.cache.TodoCacheDataSource
import com.angelo.cleanarch.business.data.network.TodoNetworkDataSource
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTodos constructor(
    private val todoNetworkDataSource: TodoNetworkDataSource,
    private val todoCacheDataSource: TodoCacheDataSource
) {

    suspend fun execute(): Flow<DataState<List<Todo>>> = flow {
        val networkTodos = todoNetworkDataSource.getAll()
        todoCacheDataSource.apply {
            deleteAll()
            insert(networkTodos)
        }

        val cachedTodos = todoCacheDataSource.getAll()

        emit(DataState.Success(cachedTodos))
    }

}