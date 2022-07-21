package com.angelo.cleanarch.framework.datasource.cache

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.angelo.cleanarch.framework.datasource.cache.database.TodoDao
import com.angelo.cleanarch.framework.datasource.cache.database.TodoDatabase
import com.angelo.cleanarch.framework.datasource.cache.model.TodoCacheEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
class TodoDaoServiceTest {

    private lateinit var todoDao: TodoDao
    private lateinit var db: TodoDatabase

    private val daoService: TodoDaoService by lazy {
        TodoDaoServiceImpl(todoDao = todoDao)
    }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, TodoDatabase::class.java
        ).build()

        todoDao = db.todoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun `When get from local data source should return entities`() = runBlocking {
        // Given
        todoDao.insert(
            listOf(
                TodoCacheEntity(1, "title 1", false),
                TodoCacheEntity(2, "title 2", false)
            )
        )

        // When
        val all = daoService.getAll()

        // Then
        Assert.assertEquals(1, all.first().id)
    }

    @Test
    fun `When get from local data source should return a entity`() = runBlocking {
        // Given
        todoDao.insert(
            listOf(
                TodoCacheEntity(1, "title 1", false),
                TodoCacheEntity(2, "title 2", false)
            )
        )

        // When
        val entity = daoService.getTodo(1)

        // Then
        Assert.assertEquals(1, entity.id)
    }

    @Test
    fun `When get from local data source should delete all`() = runBlocking {
        // Given
        todoDao.apply {
            insert(
                listOf(
                    TodoCacheEntity(1, "title 1", false),
                    TodoCacheEntity(2, "title 2", false)
                )
            )
            deleteAll()
        }

        // When
        val entities = daoService.getAll()

        // Then
        Assert.assertEquals(true, entities.isEmpty())
    }

    @Test
    fun `When save in local data source should return an entity id`() = runBlocking {
        // Given
        val id = todoDao.insert(TodoCacheEntity(1, "title 1", false))

        // Then
        Assert.assertEquals(1, id)
    }

    @Test
    fun `When save in local data source should return an entities ids`() = runBlocking {
        // Given
        val ids = todoDao.insert(
            listOf(
                TodoCacheEntity(1, "title 1", false),
                TodoCacheEntity(2, "title 2", false)
            )
        )

        // Then
        Assert.assertEquals(1, ids.first())
    }

}