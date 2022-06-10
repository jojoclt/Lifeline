package com.example.lifeline.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.lifeline.presentation.ui.theme.Red700
import com.example.lifeline.util.Screen
import com.example.lifeline.util.bottomNavItems

@Composable
fun BottomNav(
    navController: NavController,
    currentScreen: Screen,
    modifier: Modifier = Modifier
) {

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    bottomBarState.value = when (currentScreen) {
        Screen.AddTodoScreen, Screen.TodosScreen -> false
        else -> true
    }
    if (bottomBarState.value)
        BottomNavigation(backgroundColor = Color.White, elevation = 16.dp, modifier = modifier) {

            bottomNavItems.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(screen.iconId!!),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(stringResource(screen.resourceId)) },
                    onClick = {
                        navController.navigate(screen.route) {

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true

                            restoreState = true
                        }
                    },
                    selected = screen == currentScreen,
                    selectedContentColor = Red700,
                    unselectedContentColor = Color.LightGray
                )
            }
        }
}