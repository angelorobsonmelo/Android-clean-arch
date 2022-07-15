package com.angelo.cleanarch.framework.datasource.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoCacheEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val completed: Boolean
)