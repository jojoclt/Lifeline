package com.example.lifeline.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.lifeline.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int?
) {
    object HomeScreen : Screen("home_screen", R.string.home_screen, R.drawable.ic_home)
    object CalendarScreen : Screen("calendar_screen", R.string.calendar_screen, R.drawable.ic_calendar)
    object TodayScreen : Screen("today_screen", R.string.today_screen, R.drawable.ic_cup)
    object AddTodoScreen : Screen("add_todo_screen", R.string.add_todo_screen, null)

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                "home_screen" -> HomeScreen
                "calendar_screen" -> CalendarScreen
                "today_screen" -> TodayScreen
                "add_todo_screen" -> AddTodoScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")


            }
    }
}

val bottomNavItems = listOf(
    Screen.HomeScreen,
    Screen.CalendarScreen,
    Screen.TodayScreen
)
