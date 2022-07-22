package com.angelo.cleanarch.business.data.network

import com.angelo.cleanarch.framework.datasource.network.TodoRetrofitService
import com.angelo.cleanarch.framework.datasource.network.mappers.TodoNetworkMapper
import com.angelo.cleanarch.framework.datasource.network.model.TodoNetworkEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TodoNetworkDataSourceTest {

    private val todoRetrofitServiceMock: TodoRetrofitService = mock()
    private val mapper = TodoNetworkMapper()

    private val todoNetworkDataSource: TodoNetworkDataSource by lazy {
        TodoNetworkDataSourceImpl(
            todoRetrofitService = todoRetrofitServiceMock,
            todoNetworkMapper = mapper
        )
    }

    @Test
    fun `When get all from network data source should return a list`() = runBlocking {
        // Given
        whenever(todoRetrofitServiceMock.getAll()).thenReturn(
            listOf(
                TodoNetworkEntity(
                    1,
                    "Title",
                    false
                )
            )
        )

        // When
        val list = todoNetworkDataSource.getAll()

        // Then
        verify(todoRetrofitServiceMock, times(1)).getAll()
        Assert.assertEquals(1, list.size)
    }

}