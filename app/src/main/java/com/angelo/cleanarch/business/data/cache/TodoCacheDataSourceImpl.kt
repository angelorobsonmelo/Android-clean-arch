package com.angelo.cleanarch.business.data.cache

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.framework.datasource.cache.TodoDaoService
import com.angelo.cleanarch.framework.datasource.cache.mappers.TodoCacheMapper

class TodoCacheDataSourceImpl(
    private val cacheMapper: TodoCacheMapper,
    private val todoDaoService: TodoDaoService
) : TodoCacheDataSource {

    override suspend fun getAll(): List<Todo> =
        cacheMapper.mapFromEntityList(todoDaoService.getAll())

    override suspend fun getTodo(id: Int): Todo {
        return cacheMapper.mapFromEntity(todoDaoService.getTodo(id))
    }

    override suspend fun insert(models: List<Todo>): List<Long> =
        todoDaoService.insert(cacheMapper.mapToEntityList(models))

    override suspend fun insert(model: Todo): Long =
        todoDaoService.insert(cacheMapper.mapToEntity(model))

    override suspend fun deleteAll() {
        todoDaoService.deleteAll()
    }

}