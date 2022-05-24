package com.example.lifeline.util

import androidx.annotation.StringRes
import com.example.lifeline.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object HomeScreen: Screen("home_screen", R.string.home_screen)
    object CalendarScreen: Screen("calendar_screen", R.string.calendar_screen)
    object TodayScreen: Screen("today_screen", R.string.today_screen)
}

val items = listOf(
    Screen.HomeScreen,
    Screen.CalendarScreen,
    Screen.TodayScreen
)