package com.angelo.cleanarch.framework.datasource.cache.mappers

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class TodoCacheMapperTest {

    private val mapper = TodoCacheMapper()

    @Test
    fun `when mapper should map to model`() {
        // When
        val result = mapper.mapFromEntity(TodoCacheEntity(1, "title 1", false))

        // Then
        assertEquals(Todo::class, result::class)
    }

    @Test
    fun `when mapper should map to cache entity`() {
        // When
        val result = mapper.mapToEntity(Todo(1, "title 1", false))

        // Then
        assertEquals(TodoCacheEntity::class, result::class)
    }

    @Test
    fun `when mapper should map to model list`() {
        // Given
        val list = listOf(
            TodoCacheEntity(1, "title 1", false),
            TodoCacheEntity(2, "title 2", false)
        )

        // When
        val results = mapper.mapFromEntityList(list)

        // Then
        assertEquals(Todo::class, results.first()::class)
    }

    @Test
    fun `when mapper should map to cache entities`() {
        // Given
        val list = listOf(
            Todo(1, "title 1", false),
            Todo(2, "title 2", false)
        )

        // When
        val results = mapper.mapToEntityList(list)

        // Then
        assertEquals(TodoCacheEntity::class, results.first()::class)
    }
}