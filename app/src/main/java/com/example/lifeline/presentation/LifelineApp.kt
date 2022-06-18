@file:OptIn(ExperimentalMaterialApi::class)

package com.example.lifeline.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.leinardi.android.speeddial.compose.SpeedDialOverlay
import com.leinardi.android.speeddial.compose.SpeedDialState

private const val TAG = "MyActivity"

@Preview
@Composable
fun LifelineApp() {
    LifelineTheme {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        /**
         * State for the dial FAB
         */
        var speedDialState by rememberSaveable { mutableStateOf(SpeedDialState.Collapsed) }
        var overlayVisible: Boolean by rememberSaveable { mutableStateOf(speedDialState.isExpanded()) }

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

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val screen = currentRoute?.substringBefore("?")
        val fabState = rememberSaveable { (mutableStateOf(true)) }
        val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

        fabState.value = when (screen) {
            Screen.AddTodoScreen.route, Screen.AddDeadlineScreen.route -> false
            else -> true
        }
        bottomBarState.value = when (screen) {
            Screen.AddTodoScreen.route, Screen.AddDeadlineScreen.route -> false
            Screen.TodosScreen.route -> false
            else -> true
        }
        Scaffold(
            bottomBar = {
                BottomNav(
                    navController = navController,
                    modifier = Modifier.clip(
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    ),
                    bottomBarState = bottomBarState.value
                )
            },
            floatingActionButton = {
                if (bottomBarState.value) {
                    FabSpeedDial(
                        navController,
                        speedDialState = speedDialState
                    ) {
                        speedDialState = it

                    }
                }

            }) {


            NavGraph(navController)
            SpeedDialOverlay(
                visible = speedDialState.isExpanded(),
                onClick = {
                    speedDialState = speedDialState.toggle()
                },
            )
        }
    }
}

