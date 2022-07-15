package com.angelo.cleanarch.business.data.network

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.framework.datasource.network.TodoRetrofitService
import com.angelo.cleanarch.framework.datasource.network.mappers.TodoNetworkMapper
import javax.inject.Inject

class TodoNetworkDataSourceImpl @Inject constructor(
    private val todoRetrofitService: TodoRetrofitService,
    private val todoNetworkMapper: TodoNetworkMapper
) : TodoNetworkDataSource {

    override suspend fun getAll(): List<Todo> =
        todoNetworkMapper.mapFromEntityList(todoRetrofitService.getAll())
}