package com.example.lifeline.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.presentation.calendar.composables.CalendarScreen
import com.example.lifeline.presentation.home.composables.HomeScreen
import com.example.lifeline.presentation.today.composables.TodayScreen
import com.example.lifeline.util.Screen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.HomeScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.CalendarScreen.route) {
            CalendarScreen(navController)
        }
        composable(route = Screen.TodayScreen.route) {
            TodayScreen(navController)
        }
    }
}

fun NavGraphBuilder.welcomeGraph(navController: NavController) {
    navigation(startDestination = "welcome_1", route = "welcome") {

    }
}