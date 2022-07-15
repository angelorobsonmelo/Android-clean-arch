package com.angelo.cleanarch.framework.datasource.cache

import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity

interface TodoDaoService {

    suspend fun getAll(): List<TodoCacheEntity>
    suspend fun getTodo(id: Int): TodoCacheEntity
    suspend fun insert(entities: List<TodoCacheEntity>): List<Long>
    suspend fun insert(entity: TodoCacheEntity): Long
    suspend fun deleteAll()
}