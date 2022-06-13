package com.example.lifeline.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.lifeline.presentation.calendar.CalendarScreen
import com.example.lifeline.presentation.home.composables.HomeScreen
import com.example.lifeline.presentation.task.AddDeadlineScreen
import com.example.lifeline.presentation.task.AddTodoScreen
import com.example.lifeline.presentation.today.TodayScreen
import com.example.lifeline.presentation.today.TodayViewModel
import com.example.lifeline.presentation.today.TodosScreen
import com.example.lifeline.util.Screen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    val todayViewModel: TodayViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController, todayViewModel)
        }
        composable(Screen.CalendarScreen.route) {
            CalendarScreen(navController, todayViewModel)
        }
        composable(Screen.TodayScreen.route) {
            TodayScreen(navController, todayViewModel)
        }
        composable(Screen.TodosScreen.route) {
            TodosScreen(navController, todayViewModel)
        }
        composable(
            route = Screen.AddTodoScreen.route + "?taskId={taskId}",
            arguments = listOf(
                navArgument(name = "taskId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddTodoScreen(navController)
        }
        composable(
            route = Screen.AddDeadlineScreen.route + "?taskId={taskId}",
            arguments = listOf(
                navArgument(name = "taskId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddDeadlineScreen(navController)
        }

    }
}

fun NavGraphBuilder.welcomeGraph(navController: NavController) {
    navigation(startDestination = "welcome_1", route = "welcome") {

    }
}