package com.angelo.cleanarch.framework.presentation.ui.todos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.angelo.cleanarch.business.domain.interactors.GetTodo
import com.angelo.cleanarch.business.domain.interactors.GetTodos
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getTodosMock: GetTodos

    @MockK
    private lateinit var getTodoMock: GetTodo

    @RelaxedMockK
    private lateinit var todosObserver: Observer<DataState<List<Todo>>>

    @RelaxedMockK
    private lateinit var todoObserver: Observer<DataState<Todo>>

    @InjectMockKs
    private lateinit var viewModel: TodoViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxed = true)

        viewModel.todosListState.asLiveData().observeForever(todosObserver)
        viewModel.todoState.asLiveData().observeForever(todoObserver)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when fecth todos from view model should return a sequence, idle, loading and success`() {
        // Given
        val todos = listOf(
            Todo(1, "title 1", false),
            Todo(2, "title 2", true)
        )

        coEvery { getTodosMock.execute() } returns flowOf(DataState.Success(todos))

        // When
        viewModel.fetchTodos()

        // Then
        verifySequence {
            todosObserver.onChanged(DataState.Idle)
            todosObserver.onChanged(DataState.Loading)
            todosObserver.onChanged(DataState.Success(todos))
        }
    }

    @Test
    fun `When fetch todos throw an exception from view model should return a sequence idle, loading and error`() {
        // Given
        val error = "error"

        coEvery { getTodosMock.execute() } returns callbackFlow {
            throw Exception(error)
        }
        // When
        viewModel.fetchTodos()

        // Then
        verifySequence {
            todosObserver.apply {
                onChanged(DataState.Idle)
                onChanged(DataState.Loading)
                onChanged(DataState.Error(error))
            }
        }
    }

    @Test
    fun `when fecth todo from view model should return the sequences, idle, loading and success`() {
        // Given
        val id = 1
        val todo = Todo(1, "title 1", false)

        coEvery { getTodoMock.execute(eq(id)) } returns flowOf(DataState.Success(todo))

        viewModel.fetchTodo(id)

        // Then
        verifySequence {
            todoObserver.apply {
                onChanged(DataState.Idle)
                onChanged(DataState.Loading)
                onChanged(DataState.Success(todo))
            }

        }
    }

    @Test
    fun `When fetch todo throw an exception from view model should return a sequence idle, loading and error`() {
        // Given
        val error = "error"
        val id = 1

        coEvery { getTodoMock.execute(eq(id)) } returns callbackFlow {
            throw Exception(error)
        }
        // When
        viewModel.fetchTodo(id)

        // Then
        verifySequence {
            todoObserver.apply {
                onChanged(DataState.Idle)
                onChanged(DataState.Loading)
                onChanged(DataState.Error(error))
            }
        }
    }

}