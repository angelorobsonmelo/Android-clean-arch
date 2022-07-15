package com.angelo.cleanarch.business.data.network

import com.angelo.cleanarch.business.domain.models.Todo

interface TodoNetworkDataSource {

    suspend fun getAll(): List<Todo>

}