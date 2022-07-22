package com.angelo.cleanarch.business.domain.interactors

import com.angelo.cleanarch.business.data.cache.TodoCacheDataSource
import com.angelo.cleanarch.business.data.network.TodoNetworkDataSource
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*


class GetTodosTest {

    private val todoNetworkDataSourceMock: TodoNetworkDataSource = mock()
    private val todoCacheDataSourceMock: TodoCacheDataSource = mock()

    private val getTodos: GetTodos by lazy {
        GetTodos(
            todoNetworkDataSource = todoNetworkDataSourceMock,
            todoCacheDataSource = todoCacheDataSourceMock
        )
    }

    @Test
    fun `when get from get todos interactor should return success`() = runBlocking {
        // Given
        val todos = listOf(
            Todo(1, "title 1", false),
            Todo(2, "title 2", false)
        )

        whenever(todoCacheDataSourceMock.getAll()).thenReturn(todos)
        whenever(todoNetworkDataSourceMock.getAll()).thenReturn(todos)

        // When
        val flow = getTodos.execute()

        // Then
        flow
            .onStart {}
            .collect {
                verify(todoNetworkDataSourceMock, times(1)).getAll()
                verify(todoCacheDataSourceMock, times(1)).deleteAll()
                verify(todoCacheDataSourceMock, times(1)).insert(eq(todos))
                verify(todoCacheDataSourceMock, times(1)).getAll()

                assertEquals(DataState.Success(todos), it)
            }
    }

    @Test
    fun `when get from get todos interactodr should return error`() = runBlocking {
        // Given
        val errorMsg = "error"
        whenever(todoNetworkDataSourceMock.getAll()).thenAnswer {
            throw Exception(errorMsg)
        }

        // When
        val flow = getTodos.execute()

        // Then
        flow
            .onStart {}
            .catch {
                assertEquals(errorMsg, it.message)
            }
            .collect {}
    }

}