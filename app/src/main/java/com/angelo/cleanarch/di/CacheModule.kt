package com.angelo.cleanarch.di

import android.content.Context
import androidx.room.Room
import com.angelo.cleanarch.business.data.cache.TodoCacheDataSource
import com.angelo.cleanarch.business.data.cache.TodoCacheDataSourceImpl
import com.angelo.cleanarch.framework.datasource.cache.TodoDaoService
import com.angelo.cleanarch.framework.datasource.cache.TodoDaoServiceImpl
import com.angelo.cleanarch.framework.datasource.cache.database.TodoDao
import com.angelo.cleanarch.framework.datasource.cache.database.TodoDatabase
import com.angelo.cleanarch.framework.datasource.cache.mappers.TodoCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideTodoCacheMapper() = TodoCacheMapper()

    @Singleton
    @Provides
    fun provideTodoDb(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

    @Singleton
    @Provides
    fun provideDaoService(todoDao: TodoDao): TodoDaoService {
        return TodoDaoServiceImpl(todoDao)
    }

    @Singleton
    @Provides
    fun provideTodoCacheDataSource(
        cacheMapper: TodoCacheMapper,
        todoDaoService: TodoDaoService
    ): TodoCacheDataSource {
        return TodoCacheDataSourceImpl(
            cacheMapper,
            todoDaoService
        )
    }


}