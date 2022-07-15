package com.angelo.cleanarch.framework.datasource.network

import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity

interface TodoRetrofitService {

    suspend fun getAll(): List<TodoNetworkEntity>
}