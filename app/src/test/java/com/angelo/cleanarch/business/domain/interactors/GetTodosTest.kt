package com.angelo.cleanarch.business.domain.interactors

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.angelo.cleanarch.business.data.network.TodoNetworkDataSource
import com.angelo.cleanarch.framework.datasource.cache.database.TodoDao
import com.angelo.cleanarch.framework.datasource.cache.database.TodoDatabase
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.mockito.kotlin.mock
import java.io.IOException

class GetTodosTest {

    private lateinit var todoDao: TodoDao
    private lateinit var db: TodoDatabase
    private val todoNetworkDataSourceMock: TodoNetworkDataSource = mock()


//    private val getTodos by lazy {
//        GetTodos(
//            todoCacheDataSource = todoDao,
//            todoNetworkDataSource = todoNetworkDataSourceMock
//        )
//    }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, TodoDatabase::class.java
        ).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


}