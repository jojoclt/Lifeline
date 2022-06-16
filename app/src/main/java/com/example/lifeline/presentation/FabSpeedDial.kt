package com.example.lifeline.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.lifeline.R
import com.example.lifeline.presentation.ui.theme.DeadlineColor
import com.example.lifeline.presentation.ui.theme.TodoColor
import com.example.lifeline.util.Screen
import com.leinardi.android.speeddial.compose.FabWithLabel
import com.leinardi.android.speeddial.compose.SpeedDial
import com.leinardi.android.speeddial.compose.SpeedDialState

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun FabSpeedDial(navController: NavController, speedDialState: SpeedDialState ,updateState: (Boolean, SpeedDialState) -> Unit) {
    SpeedDial(
        state = speedDialState,
        onFabClick = { expanded ->
            updateState(expanded, speedDialState.toggle())
        },
        fabClosedContentColor = Color.White,
        fabOpenedContentColor = Color.White
    ) {
        item {
            FabWithLabel(
                onClick = {
                    updateState(false, speedDialState.toggle())
                    navController.navigate(Screen.AddTodoScreen.route)
                },
                labelContent = { Text(text = "Add Todo") },
                fabBackgroundColor = TodoColor
            ) {
                Icon(painterResource(id = R.drawable.ic_todo), null)
            }
        }
        item {
            FabWithLabel(
                onClick = {
                    updateState(false, speedDialState.toggle())
                    navController.navigate(Screen.AddDeadlineScreen.route)

                },
                labelContent = { Text(text = "Add Deadline") },
                fabBackgroundColor = DeadlineColor
            ) {
                Icon(painterResource(id = R.drawable.ic_deadline), null)
            }
        }
    }
}