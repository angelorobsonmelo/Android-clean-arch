package com.angelo.cleanarch.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoCacheEntity: TodoCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoCacheEntity: List<TodoCacheEntity>): List<Long>

    @Query("SELECT * FROM TodoCacheEntity")
    suspend fun get(): List<TodoCacheEntity>

    @Query("SELECT * FROM TodoCacheEntity WHERE id = :id")
    suspend fun getTodo(id: Int): TodoCacheEntity

    @Query("DELETE FROM TodoCacheEntity")
    suspend fun deleteAll()
}