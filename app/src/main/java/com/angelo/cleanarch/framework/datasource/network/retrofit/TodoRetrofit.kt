package com.angelo.cleanarch.framework.datasource.network.retrofit

import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity
import retrofit2.http.GET

interface TodoRetrofit {

    @GET("todos")
    suspend fun getAll(): List<TodoNetworkEntity>
}