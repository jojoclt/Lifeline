package com.example.lifeline.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.lifeline.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object HomeScreen : Screen("home_screen", R.string.home_screen, R.drawable.ic_home)
    object CalendarScreen : Screen("calendar_screen", R.string.calendar_screen, R.drawable.ic_calendar)
    object TodayScreen : Screen("today_screen", R.string.today_screen, R.drawable.ic_cup)
}

val items = listOf(
    Screen.HomeScreen,
    Screen.CalendarScreen,
    Screen.TodayScreen
)