package com.example.lifeline.presentation.calendar.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.presentation.ui.theme.PrimaryColor
import com.example.lifeline.util.Screen

@Composable
fun CalendarScreen(navController: NavController) {
    Surface() {
        Column {
            WeekDay()
        }
    }
}

@Composable
fun WeekDay() {
    val weeks = arrayOf(
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                PrimaryColor
            )
    ) {
        weeks.forEach { week ->
            Spacer(
                Modifier
                    .weight(1f)
            )
            Text(week, color = Color.Black)
            Spacer(
                Modifier
                    .weight(1f)
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun CalendarScreenPreview() {
    val navController = rememberNavController()
    val allScreens = Screen.values().toList()
    LifelineTheme {
        Scaffold(
            topBar = { TopNav(title = "Calendar") },
            content = { CalendarScreen(navController) },
            bottomBar = {
                BottomNav(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = Screen.Calendar
                )
            }
        )
    }
}