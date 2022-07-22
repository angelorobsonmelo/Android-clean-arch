package com.angelo.cleanarch.business.domain.interactors

import com.angelo.cleanarch.business.data.cache.TodoCacheDataSource
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.mockito.kotlin.*

class GetTodoTest {

    private val todoCacheDataSourceMock: TodoCacheDataSource = mock()

    private val getTodo: GetTodo by lazy {
        GetTodo(
            cacheDataSource = todoCacheDataSourceMock
        )
    }

    @Test
    fun `when get from get dodo interector should return success`() = runBlocking {
        // Given
        val id = 1
        val todo = Todo(1, "title", false)
        whenever(todoCacheDataSourceMock.getTodo(eq(id))).thenReturn(todo)

        // When
        val flow = getTodo.execute(id)

        // Then
        flow
            .onStart { }
            .collect {
                verify(todoCacheDataSourceMock, times(1)).getTodo(eq(id))

                assertEquals(DataState.Success(todo), it)
            }
    }

    @Test
    fun `when get from get todo interactor should return error`() = runBlocking {
        // Given
        val errorMsg = "error"
        val id = 1
        whenever(todoCacheDataSourceMock.getTodo(id)).thenAnswer {
            throw Exception(errorMsg)
        }

        // When
        val flow = getTodo.execute(id)

        // Then
        flow
            .onStart {}
            .catch {
                assertEquals(errorMsg, it.message)
            }
            .collect {}
    }

}