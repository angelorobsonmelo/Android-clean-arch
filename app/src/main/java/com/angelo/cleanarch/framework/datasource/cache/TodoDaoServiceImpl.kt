package com.angelo.cleanarch.framework.datasource.cache

import com.angelo.cleanarch.framework.datasource.cache.database.TodoDao
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity

class TodoDaoServiceImpl(
    private val todoDao: TodoDao
) : TodoDaoService {

    override suspend fun getAll(): List<TodoCacheEntity> {
        return todoDao.get()
    }

    override suspend fun getTodo(id: Int): TodoCacheEntity = todoDao.getTodo(id)

    override suspend fun insert(entities: List<TodoCacheEntity>): List<Long> {
        return todoDao.insert(entities)
    }

    override suspend fun insert(entity: TodoCacheEntity): Long {
        return todoDao.insert(entity)
    }

    override suspend fun deleteAll() = todoDao.deleteAll()
}