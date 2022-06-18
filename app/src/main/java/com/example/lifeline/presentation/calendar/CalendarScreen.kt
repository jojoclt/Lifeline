package com.example.lifeline.presentation.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.task.composables.TasksList
import com.example.lifeline.presentation.today.TodayEvent
import com.example.lifeline.presentation.today.TodayViewModel
import com.example.lifeline.presentation.ui.theme.PrimaryColor
import com.example.lifeline.presentation.ui.theme.Red700
import com.example.lifeline.util.Screen
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Composable
fun CalendarScreen(navController: NavController, viewModel: TodayViewModel = hiltViewModel()) {
    val currentScreen = Screen.CalendarScreen

    val date = remember { mutableStateOf(LocalDate.now()) }
    Scaffold(
        topBar = {
            TopNav(currentScreen)
        },
    ) {
        Column() {
            val taskList = viewModel.getAllTasksForCalendar()
            Surface {
                Kalendar(
                    selectedDay = date.value,
                    kalendarType = KalendarType.Firey(),
                    onCurrentDayClick = { day, event ->
                        //handle the date click listener
                        viewModel.getTaskAll(
                            Date.from(
                                day.atStartOfDay(ZoneId.systemDefault()).toInstant()
                            )
                        )
                        date.value = day
                    },
                    errorMessage = {
                        //Handle the error if any
                    },
                    kalendarStyle = KalendarStyle(
                        hasRadius = true,
                        kalendarBackgroundColor = PrimaryColor,
                        kalendarSelector = KalendarSelector.Circle(
//                            selectedColor = Red500,
//                            todayColor = Red700,
                            eventTextColor = Red700
                        ),
                    ),
                    kalendarEvents = taskList
                )

            }
            TasksList(viewModel, navController)
        }

    }
}
