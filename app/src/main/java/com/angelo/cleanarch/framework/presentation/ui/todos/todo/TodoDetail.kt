package com.angelo.cleanarch.framework.presentation.ui.todos.todo

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.angelo.cleanarch.R
import com.angelo.cleanarch.business.domain.models.Todo
import com.angelo.cleanarch.business.domain.state.DataState
import com.angelo.cleanarch.framework.presentation.ui.components.TodoItem
import com.angelo.cleanarch.framework.presentation.ui.theme.topAppBarContentColor

@Composable
fun TodoDetailScreen(dataState: DataState<Todo>, onclickBackStack: () -> Unit) {

    Scaffold(
        topBar = { TodoDetailAppBar(onclickBackStack) },
        content = {
            when (dataState) {
                is DataState.Error -> {

                }
                DataState.Idle -> {

                }
                DataState.Loading -> {

                }
                is DataState.Success -> {
                    TodoItem(dataState.data)
                }
            }
        }
    )
}

@Composable
fun TodoDetailAppBar(
    onclickBackStack: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onclickBackStack)
        },
        title = {
            Text(
                text = stringResource(id = R.string.todo_details),
            )
        },
    )
}

@Composable
fun BackAction(
    onBackClicked: () -> Unit
) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.todo_details),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}