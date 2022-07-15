package com.angelo.cleanarch.framework.datasource.network

import android.util.Log
import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity
import com.angelo.cleanarch.framework.datasource.network.retrofit.TodoRetrofit

class TodoRetrofitServiceImpl constructor(
    private val todoRetrofit: TodoRetrofit
) : TodoRetrofitService {

    override suspend fun getAll(): List<TodoNetworkEntity> {
        val todoNetworkEntity = todoRetrofit.getAll()
        Log.d(LOG_TAG, "Todo Details are $todoNetworkEntity")
        return todoNetworkEntity
    }

    companion object {
        private val LOG_TAG = TodoRetrofitServiceImpl::class.java.simpleName
    }
}