package com.angelo.cleanarch.framework.presentation.ui.todos.todos

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.angelo.cleanarch.di.CacheModule
import com.angelo.cleanarch.di.RetrofitModule
import com.angelo.cleanarch.framework.presentation.navigation.Navigation
import com.angelo.cleanarch.framework.presentation.ui.MainActivity
import com.angelo.cleanarch.framework.presentation.ui.theme.CleanArchTheme
import com.angelo.cleanarch.utils.FileUtils
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val serverPort = 8080

@HiltAndroidTest
@UninstallModules(CacheModule::class, RetrofitModule::class)
class TodosScreenTest {

    private val server = MockWebServer()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        setUpMockServer()
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()

            CleanArchTheme {
                CleanArchTheme {
                    Navigation(
                        navController = navController
                    )
                }
            }
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun checkElementsAreDisplayed() {
        composeRule.onNodeWithText("Todos").assertIsDisplayed()
        composeRule.onNodeWithText("delectus aut autem").assertIsDisplayed()
        composeRule.onNodeWithText("Congratulations! This task is done!").assertIsDisplayed()
        composeRule.onAllNodes(hasTestTag("status"), true).onFirst().assertExists()
    }

    @Test
    fun clickOnItemAndCheckDetailScreenAreDisplayed() {
        composeRule.onAllNodes(hasTestTag("status"), true).onFirst().performClick()
        composeRule.onNodeWithText("Todo Details").assertIsDisplayed()
        composeRule.onNodeWithText("Todos").assertDoesNotExist()
    }

    private fun setUpMockServer() {
        server.start(serverPort)

        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/todos" -> {
                        successResponse
                    }
                    else -> {
                        errorResponse
                    }
                }
            }
        }
    }


    companion object {
        private val successResponse by lazy {
            MockResponse()
                .setResponseCode(200)
                .setBody(FileUtils.getJson("assets/todos_response.json"))
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(503) }
    }

}