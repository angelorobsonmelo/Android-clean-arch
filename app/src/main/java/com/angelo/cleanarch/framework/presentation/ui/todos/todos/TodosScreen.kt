package com.angelo.cleanarch.framework.presentation.ui.todos.todos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.angelo.cleanarch.R
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import com.angelo.cleanarch.framework.presentation.ui.components.ProgressBar
import com.angelo.cleanarch.framework.presentation.ui.components.TodoItem

@Composable
fun TodosScreen(
    dataState: DataState<List<Todo>>,
    onclickItem: (id: Int) -> Unit
) {

    var progressBarVisibility by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.Todos)) }
            )
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                when (dataState) {
                    is DataState.Error -> {
                        progressBarVisibility = false
                    }
                    DataState.Idle -> {
                        progressBarVisibility = false
                    }
                    DataState.Loading -> {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ProgressBar(visibility = progressBarVisibility)
                        }
                    }
                    is DataState.Success -> {
                        progressBarVisibility = false

                        LazyColumn {
                            items(
                                items = dataState.data,
                                key = { todo ->
                                    todo.id
                                },
                                itemContent = { item ->
                                    TodoItem(item) {
                                        onclickItem.invoke(item.id)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    )

}