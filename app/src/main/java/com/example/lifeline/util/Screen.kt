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
    object EditTodoScreen : Screen("edit_todo_screen", R.string.edit_todo_screen, null)
    object AddDeadlineScreen : Screen("add_deadline_screen", R.string.add_deadline_screen, null)
    object EditDeadlineScreen : Screen("edit_deadline_screen", R.string.edit_deadline_screen, null)
    object TodosScreen : Screen("todos_list_screen",R.string.todos_list_screen,null)

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                "home_screen" -> HomeScreen
                "calendar_screen" -> CalendarScreen
                "today_screen" -> TodayScreen
                "add_todo_screen" -> AddTodoScreen
                "todos_list_screen"-> TodosScreen
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

val AddTaskItems = listOf(
    Screen.AddTodoScreen,
    Screen.AddDeadlineScreen
)

val EditTaskItems = listOf(
    Screen.EditTodoScreen,
    Screen.EditDeadlineScreen
)

val ListItems = listOf(
    Screen.TodosScreen
)

