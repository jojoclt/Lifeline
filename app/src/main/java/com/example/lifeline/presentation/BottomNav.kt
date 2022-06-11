package com.example.lifeline.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lifeline.presentation.ui.theme.Red700
import com.example.lifeline.util.Screen
import com.example.lifeline.util.bottomNavItems

@Composable
fun BottomNav(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    bottomBarState.value = when (currentRoute) {
        Screen.AddTodoScreen.route -> false
        Screen.TodosScreen.route -> false
        else -> true
    }
    if (bottomBarState.value)
        BottomNavigation(backgroundColor = Color.White, elevation = 16.dp, modifier = modifier) {

            bottomNavItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(item.iconId!!),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(stringResource(item.resourceId)) },
                    onClick = {
                        navController.navigate(item.route) {

                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true

                            restoreState = true
                        }
                    },
                    selected = item.route == currentRoute,
                    selectedContentColor = Red700,
                    unselectedContentColor = Color.LightGray
                )
            }
        }
}