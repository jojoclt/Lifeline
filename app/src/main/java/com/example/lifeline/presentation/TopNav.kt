package com.example.lifeline.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lifeline.R
import com.example.lifeline.util.Screen

@Composable
fun TopNav(
    currentScreen: Screen, modifier: Modifier = Modifier
) {
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val calendarState = rememberSaveable { (mutableStateOf(true)) }

    calendarState.value = when (currentScreen) {
        Screen.CalendarScreen -> true
        else -> false
    }
    topBarState.value = when (currentScreen) {
        Screen.CalendarScreen -> false
        else -> true
    }
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.resourceId)) },
        actions = {

            if (calendarState.value) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(id = R.drawable.ic_today),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painterResource(id = R.drawable.ic_help),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

            }

        },
        elevation = 0.dp
    )

}