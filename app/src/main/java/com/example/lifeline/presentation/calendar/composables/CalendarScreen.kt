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
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.Day
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

@Composable
fun CalendarScreen(navController: NavController) {
    val currentScreen = Screen.CalendarScreen
    val calendarState = rememberSelectableCalendarState()
    calendarState.monthState.currentMonth = YearMonth.of(2020, 5)
    calendarState.selectionState.selection = listOf(LocalDate.parse("2020-05-02"))
    calendarState.selectionState.selectionMode = SelectionMode.Single
    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Surface {
//            Column {
//                WeekDay()
//            }
            SelectableCalendar(
//                dayContent = {dayState -> MyDay(dayState) }
                calendarState = calendarState
            )
        }
    }
}

@Composable
fun MyDay(dayState: DayState<DynamicSelectionState>) {
    Text(dayState.date.dayOfMonth.toString())
    
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
    val currentScreen = Screen.CalendarScreen
    LifelineTheme {
        Scaffold(
            content = { CalendarScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        )
    }
}