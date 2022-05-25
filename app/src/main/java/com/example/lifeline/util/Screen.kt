package com.example.lifeline.util

import androidx.annotation.DrawableRes
import com.example.lifeline.R


enum class Screen(@DrawableRes val icon: Int) {
    Home(icon = R.drawable.ic_home),
    Calendar(icon = R.drawable.ic_calendar),
    Today(icon = R.drawable.ic_cup);

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Calendar.name -> Calendar
                Today.name -> Today
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}