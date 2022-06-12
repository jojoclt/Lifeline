@file:OptIn(ExperimentalMaterialApi::class)

package com.example.lifeline.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
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
import com.leinardi.android.speeddial.compose.FabWithLabel
import com.leinardi.android.speeddial.compose.SpeedDial
import com.leinardi.android.speeddial.compose.SpeedDialOverlay
import com.leinardi.android.speeddial.compose.SpeedDialState

private const val TAG = "MyActivity"

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
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
//        val backstackEntry = navController.currentBackStackEntryAsState()
//        val currentScreen = Screen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            bottomBar = {
                BottomNav(
                    navController = navController,
                    modifier = Modifier.clip(
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    )
                )
            },
            floatingActionButton = {
                SpeedDial(
                    state = speedDialState,
                    onFabClick = { expanded ->
                        overlayVisible = !expanded
                        speedDialState = speedDialState.toggle()
                    },
                ) {
                    item {
                        FabWithLabel(
                            onClick = { /*showToast(context, "Item 1 clicked!")*/ },
                            labelContent = { Text(text = "Item 1") },
                        ) {
                            Icon(Icons.Default.Share, null)
                        }
                    }
                    item {
                        FabWithLabel(
                            onClick = { /*showToast(context, "Item 2 clicked!")*/ },
                            labelContent = { Text(text = "Item 2") },
                        ) {
                            Icon(Icons.Default.Share, null)
                        }
                    }
                }

                //FABElement(navController = navController)
            }) { innerPadding ->
            SpeedDialOverlay(
                visible = overlayVisible,
                onClick = {
                    overlayVisible = false
                    speedDialState = speedDialState.toggle()
                },
            )
            Box(modifier = Modifier.padding(innerPadding)) {
                NavGraph(navController)
            }
        }
    }
}

