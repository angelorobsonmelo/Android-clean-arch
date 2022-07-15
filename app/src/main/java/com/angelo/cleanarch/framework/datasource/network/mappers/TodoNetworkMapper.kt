package com.angelo.cleanarch.framework.datasource.network.mappers

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.utils.EntityMapper
import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity
import javax.inject.Inject

class TodoNetworkMapper @Inject constructor() : EntityMapper<TodoNetworkEntity, Todo> {

    override fun mapFromEntity(entity: TodoNetworkEntity) = Todo(
        id = entity.id,
        title = entity.title,
        completed = entity.completed
    )

    override fun mapToEntity(domainModel: Todo) = TodoNetworkEntity(
        id = domainModel.id,
        title = domainModel.title,
        completed = domainModel.completed
    )

    override fun mapFromEntityList(entityList: List<TodoNetworkEntity>) =
        entityList.map { entity ->
            Todo(
                id = entity.id,
                title = entity.title,
                completed = entity.completed
            )
        }

    override fun mapToEntityList(domainModelList: List<Todo>) =
        domainModelList.map { domainModel ->
            TodoNetworkEntity(
                id = domainModel.id,
                title = domainModel.title,
                completed = domainModel.completed
            )
        }

}