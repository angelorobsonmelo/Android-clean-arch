package com.angelo.cleanarch.framework.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.angelo.cleanarch.framework.presentation.ui.todos.TodoViewModel
import com.angelo.cleanarch.framework.presentation.ui.todos.todo.TodoDetailScreen
import com.angelo.cleanarch.framework.presentation.ui.todos.todos.TodosScreen
import com.angelo.cleanarch.framework.presentation.utils.Constants.ID_ARGUMENT_KEY


@Composable
fun Navigation(
    todoViewModel: TodoViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.TodosScreen.route) {
        composable(route = Screen.TodosScreen.route) {
            val dataState by todoViewModel.todosListState.collectAsState()

            TodosScreen(
                dataState = dataState
            ) { id ->
                navController.navigate(
                    route = Screen.TodoDetail.route.replace(
                        "{id}",
                        "$id"
                    )
                )
            }
        }

        composable(
            route = Screen.TodoDetail.route,
            arguments = listOf(navArgument(ID_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val dataState by todoViewModel.todoState.collectAsState()

            val argId = navBackStackEntry.arguments?.getInt(ID_ARGUMENT_KEY) ?: -1

            LaunchedEffect(key1 = argId) {
                todoViewModel.fetchTodo(argId)
            }

            TodoDetailScreen(dataState) {
                navController.popBackStack()
            }
        }
    }
}