package com.example.lifeline.presentation.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.task.composables.TasksList
import com.example.lifeline.presentation.today.TodayViewModel
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarScreen(navController: NavController, viewModel: TodayViewModel = hiltViewModel()) {
    val currentScreen = Screen.CalendarScreen
//    val calendarState = rememberSelectableCalendarState()
    val currentDate = rememberSaveable { mutableStateOf(LocalDate.now()) }
    var task = viewModel.state.value.tasks

    Scaffold(
        topBar = {
            TopNav(currentScreen)
        },
    ) {
        Column() {
            Surface {
                Kalendar(
                    kalendarType = KalendarType.Firey(),
                    onCurrentDayClick = { day, event ->
                        //handle the date click listener
                        task = viewModel.getTasks(Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        currentDate.value = day
                    },
                    errorMessage = {
                        //Handle the error if any
                    },
                    kalendarStyle = KalendarStyle(
                        hasRadius = false,
                        kalendarBackgroundColor = Color.White
                    ),
                )
//                SelectableCalendar(
////                dayContent = {dayState -> MyDay(dayState) },
//                    calendarState = calendarState,
//                    monthHeader = {},
//                    monthContainer = { content ->
//                        Box(modifier = Modifier.height(340.dp)) { content(PaddingValues(vertical = 4.dp)) }
//                    }
//                )
            }
            TasksList(task,navController)
        }

    }
}

@Composable
fun MyDay(dayState: DayState<DynamicSelectionState>) {
    Text(dayState.date.dayOfMonth.toString())
    Text("A")

}


@Composable
fun TitleCalendar(monthState: MonthState) {
    Text(monthState.currentMonth.month
        .getDisplayName(TextStyle.FULL, Locale.getDefault())
        .lowercase()
        .replaceFirstChar { it.titlecase() }, style = MaterialTheme.typography.h6
    )
    Text(" ")
    Text(text = monthState.currentMonth.year.toString(), style = MaterialTheme.typography.h6)
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
                    navController = navController)
            }
        )
    }
}