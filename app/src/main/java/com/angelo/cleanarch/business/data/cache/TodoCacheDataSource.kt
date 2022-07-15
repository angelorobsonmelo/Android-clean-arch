package com.angelo.cleanarch.business.data.cache

import com.angelo.cleanarch.business.domain.models.Todo

interface TodoCacheDataSource {

    suspend fun getAll(): List<Todo>
    suspend fun getTodo(id: Int): Todo
    suspend fun insert(models: List<Todo>): List<Long>
    suspend fun insert(model: Todo): Long
    suspend fun deleteAll()
}