package com.angelo.cleanarch.framework.datasource.cache.mappers

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.utils.EntityMapper
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity
import javax.inject.Inject

class TodoCacheMapper @Inject constructor() : EntityMapper<TodoCacheEntity, Todo> {


    override fun mapFromEntity(entity: TodoCacheEntity) = Todo(
        id = entity.id,
        title = entity.title,
        completed = entity.completed
    )

    override fun mapToEntity(domainModel: Todo) = TodoCacheEntity(
        id = domainModel.id,
        title = domainModel.title,
        completed = domainModel.completed
    )

    override fun mapFromEntityList(entityList: List<TodoCacheEntity>): List<Todo> {
        return entityList.map { entity ->
            Todo(
                id = entity.id,
                title = entity.title,
                completed = entity.completed
            )
        }
    }

    override fun mapToEntityList(domainModelList: List<Todo>): List<TodoCacheEntity> {
        return domainModelList.map { domainModel ->
            TodoCacheEntity(
                id = domainModel.id,
                title = domainModel.title,
                completed = domainModel.completed
            )
        }
    }
}