package com.angelo.cleanarch.framework.presentation.navigation

sealed class Screen(
    val route: String
) {
    object TodosScreen : Screen("todos_screen")
    object TodoDetail : Screen("todo_detail/{id}")
}