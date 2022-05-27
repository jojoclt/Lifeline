package com.example.lifeline.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.lifeline.presentation.calendar.composables.CalendarScreen
import com.example.lifeline.presentation.home.composables.HomeScreen
import com.example.lifeline.presentation.task.composables.AddTodoScreen
import com.example.lifeline.presentation.today.composables.TodayScreen
import com.example.lifeline.util.Screen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.CalendarScreen.route) {
            CalendarScreen(navController)
        }
        composable(Screen.TodayScreen.route) {
            TodayScreen(navController)
        }
        composable(Screen.AddTodoScreen.route) {
            AddTodoScreen()
        }
    }
}

fun NavGraphBuilder.welcomeGraph(navController: NavController) {
    navigation(startDestination = "welcome_1", route = "welcome") {

    }
}