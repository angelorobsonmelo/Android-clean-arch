package com.angelo.cleanarch.di

import com.angelo.cleanarch.business.data.cache.TodoCacheDataSource
import com.angelo.cleanarch.business.data.network.TodoNetworkDataSource
import com.angelo.cleanarch.business.data.network.TodoNetworkDataSourceImpl
import com.angelo.cleanarch.business.domain.interactors.GetTodo
import com.angelo.cleanarch.business.domain.interactors.GetTodos
import com.angelo.cleanarch.framework.datasource.network.TodoRetrofitService
import com.angelo.cleanarch.framework.datasource.network.TodoRetrofitServiceImpl
import com.angelo.cleanarch.framework.datasource.network.mappers.TodoNetworkMapper
import com.angelo.cleanarch.framework.datasource.network.retrofit.TodoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideTodoNetworkMapper() = TodoNetworkMapper()

    @Singleton
    @Provides
    fun provideTodoRetrofitService(todoRetrofit: TodoRetrofit): TodoRetrofitService {
        return TodoRetrofitServiceImpl(todoRetrofit)
    }

    @Singleton
    @Provides
    fun provideTodoNetworkDataSource(
        todoRetrofitService: TodoRetrofitService,
        todoNetworkMapper: TodoNetworkMapper
    ): TodoNetworkDataSource {
        return TodoNetworkDataSourceImpl(todoRetrofitService, todoNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideGetTodos(
        todoNetworkDataSource: TodoNetworkDataSource,
        todoCacheDataSource: TodoCacheDataSource
    ): GetTodos {
        return GetTodos(
            todoNetworkDataSource = todoNetworkDataSource,
            todoCacheDataSource = todoCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideGetTodo(
        todoCacheDataSource: TodoCacheDataSource
    ): GetTodo {
        return GetTodo(
            cacheDataSource = todoCacheDataSource
        )
    }

}