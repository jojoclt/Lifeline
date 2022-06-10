package com.example.lifeline.presentation

import androidx.compose.foundation.layout.offset
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.lifeline.util.Screen

@Composable
fun FABElement(navController: NavController, currentScreen: Screen) {
    val fabState = rememberSaveable { (mutableStateOf(true)) }

    fabState.value = when (currentScreen) {
        Screen.AddTodoScreen, Screen.TodosScreen -> false
        else -> true
    }
    if (fabState.value)
        FloatingActionButton(modifier = Modifier.offset(0.dp,(-60).dp),onClick = {
            navController.navigate(Screen.AddTodoScreen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }) { Icon(Icons.Filled.Add, "", tint = Color.White) }
}