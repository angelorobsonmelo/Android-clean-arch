package com.angelo.cleanarch.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity

@Database(entities = [TodoCacheEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {
        val DATABASE_NAME: String = "todo_db"
    }
}