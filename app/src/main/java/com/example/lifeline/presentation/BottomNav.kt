package com.example.lifeline.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.presentation.ui.theme.Red700
import com.example.lifeline.util.items

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    BottomNavigation(modifier, backgroundColor = Color.White, elevation = 16.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(screen.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentRoute == screen.route,
                selectedContentColor = Red700,
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}