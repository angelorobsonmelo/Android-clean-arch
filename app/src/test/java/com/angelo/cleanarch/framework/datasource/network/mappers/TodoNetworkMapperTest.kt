package com.angelo.cleanarch.framework.datasource.network.mappers

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity
import org.junit.Assert.*

import org.junit.Test

class TodoNetworkMapperTest {

    private val mapper = TodoNetworkMapper()

    @Test
    fun `when mapper should map to model`() {
        // When
        val result = mapper.mapFromEntity(TodoNetworkEntity(1, "title 1", false))

        // Then
        assertEquals(Todo::class, result::class)
    }

    @Test
    fun `when mapper should map to network entity`() {
        // When
        val result = mapper.mapToEntity(Todo(1, "title 1", false))

        // Then
        assertEquals(TodoNetworkEntity::class, result::class)
    }

    @Test
    fun `when mapper should map to model list`() {
        // When
        val result = mapper.mapFromEntityList(
            listOf(
                TodoNetworkEntity(1, "title 1", false),
                TodoNetworkEntity(2, "title 2", false)
            )
        )

        // Then
        assertEquals(Todo::class, result.first()::class)
    }

    @Test
    fun `when mapper should map to network entities`() {
        // When
        val result = mapper.mapToEntityList(
            listOf(
                Todo(1, "title 1", false),
                Todo(2, "title 2", false)
            )
        )

        // Then
        assertEquals(TodoNetworkEntity::class, result.first()::class)
    }
}