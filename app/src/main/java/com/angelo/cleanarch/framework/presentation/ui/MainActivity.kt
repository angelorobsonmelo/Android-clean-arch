package com.angelo.cleanarch.framework.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.angelo.cleanarch.framework.presentation.ui.todos.TodoViewModel
import com.angelo.cleanarch.framework.presentation.navigation.Navigation
import com.angelo.cleanarch.framework.presentation.ui.theme.CleanArchTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CleanArchTheme {
                Navigation(
                    todoViewModel = todoViewModel,
                    navController = rememberNavController()
                )
            }
        }
    }
}