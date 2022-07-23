package com.angelo.cleanarch.framework.presentation.ui.todos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import com.angelo.cleanarch.business.domain.interactors.GetTodo
import com.angelo.cleanarch.business.domain.interactors.GetTodos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodos: GetTodos,
    private val getTodo: GetTodo
) : ViewModel() {

    private val _todosListState: MutableStateFlow<DataState<List<Todo>>> =
        MutableStateFlow(DataState.Idle)
    val todosListState: StateFlow<DataState<List<Todo>>> get() = _todosListState

    private val _todoState: MutableStateFlow<DataState<Todo>> =
        MutableStateFlow(DataState.Idle)
    val todoState: StateFlow<DataState<Todo>> get() = _todoState

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            getTodos.execute()
                .onStart {
                    _todosListState.value = DataState.Loading
                }
                .catch {
                    _todosListState.value = DataState.Error(it.message ?: "")
                }
                .collect {
                    _todosListState.value = it
                }
        }
    }

    fun fetchTodo(id: Int) {
        viewModelScope.launch {
            getTodo.execute(id)
                .onStart {
                    _todoState.value = DataState.Loading
                }
                .catch {
                    _todoState.value = DataState.Error(it.message ?: "")
                }
                .collect {
                    _todoState.value = it
                }
        }
    }

}