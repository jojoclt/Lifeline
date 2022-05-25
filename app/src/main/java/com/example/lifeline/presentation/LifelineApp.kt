package com.example.lifeline.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun LifelineApp() {
    LifelineTheme {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        SideEffect {
            systemUiController.setNavigationBarColor(
                color = Color.White, //Your color
                darkIcons = useDarkIcons
            )
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
        val navController = rememberNavController()
        val allScreens = Screen.values().toList()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = Screen.fromRoute(backstackEntry.value?.destination?.route)
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

        bottomBarState.value = when (currentScreen) {
            Screen.Calendar -> false
            else -> true
        }
        Scaffold(
            topBar = { TopNav(title = currentScreen.name)},
            bottomBar = {
                BottomNav(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name) {
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
                    },
                    currentScreen = currentScreen,
                    modifier = Modifier.clip(
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add, "", tint = Color.White)
                }
            }
        ) { innerPadding ->
            NavGraph(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}