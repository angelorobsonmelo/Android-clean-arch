package com.angelo.cleanarch.framework.datasource.network

import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity
import com.angelo.cleanarch.framework.datasource.network.retrofit.TodoRetrofit
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class TodoRetrofitServiceTest {

    private val todoRetrofitMock: TodoRetrofit = mock()

    private val todoRetrofitService: TodoRetrofitService by lazy {
        TodoRetrofitServiceImpl(
            todoRetrofit = todoRetrofitMock
        )
    }

    @Test
    fun `When get from remote data source should return success`() = runBlocking {
        // Given
        whenever(todoRetrofitMock.getAll()).thenReturn(
            listOf(
                TodoNetworkEntity(
                    1,
                    title = "title 1",
                    completed = false
                )
            )
        )

        // When
        val result = todoRetrofitService.getAll()

        // Then
        verify(todoRetrofitMock, times(1)).getAll()
        assertEquals(1, result.first().id)
    }

}