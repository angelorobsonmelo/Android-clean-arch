package com.angelo.cleanarch.business.data.cache

import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.framework.datasource.cache.TodoDaoService
import com.angelo.cleanarch.framework.datasource.cache.mappers.TodoCacheMapper
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*

class TodoCacheDataSourceTest {

    private val todoDaoServiceMock: TodoDaoService = mock()

    private val todoCacheDataSource: TodoCacheDataSource by lazy {
        TodoCacheDataSourceImpl(
            cacheMapper = TodoCacheMapper(),
            todoDaoService = todoDaoServiceMock

        )
    }

    @Test
    fun `When get all from cache datasource should return a list of models`() = runBlocking {
        // Given
        whenever(todoDaoServiceMock.getAll()).thenReturn(
            listOf(
                TodoCacheEntity(1, "title 1", false),
                TodoCacheEntity(2, "title 2", false)
            )
        )

        // When
        val models = todoCacheDataSource.getAll()

        // Then
        verify(todoDaoServiceMock, times(1)).getAll()
        Assert.assertEquals(1, models.first().id)
    }

    @Test
    fun `When get from cache datasource should return a model`() = runBlocking {
        // Given
        whenever(todoDaoServiceMock.getTodo(eq(1))).thenReturn(TodoCacheEntity(1, "title 2", false))

        // When
        val model = todoCacheDataSource.getTodo(1)

        // Then
        verify(todoDaoServiceMock, times(1)).getTodo(eq(1))
        Assert.assertEquals(1, model.id)
    }

    @Test
    fun `When save in cache datasource should return a id`() = runBlocking {
        // Given
        val entity = TodoCacheEntity(1, "title 2", false)

        whenever(todoDaoServiceMock.insert(entity)).thenReturn(1)

        // When
        val id = todoCacheDataSource.insert(Todo(1, "title 2", false))

        // Then
        verify(todoDaoServiceMock, times(1)).insert(entity)
        Assert.assertEquals(1, id)
    }

    @Test
    fun `When save a list in cache datasource should return ids`() = runBlocking {
        // Given
        val entities = listOf(
            TodoCacheEntity(1, "title 1", false),
            TodoCacheEntity(2, "title 2", false)
        )
        val listTodos = listOf(
            Todo(1, "title 1", false),
            Todo(2, "title 2", false)
        )

        whenever(todoDaoServiceMock.insert(entities)).thenReturn(listOf(1, 2))

        // When
        val ids = todoCacheDataSource.insert(listTodos)

        // Then
        verify(todoDaoServiceMock, times(1)).insert(entities)
        Assert.assertEquals(1, ids.first())
    }

}