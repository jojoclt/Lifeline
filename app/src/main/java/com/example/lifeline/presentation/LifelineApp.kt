package com.example.lifeline.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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

private const val TAG = "MyActivity"

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
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = Screen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            bottomBar = {
                BottomNav(
                    navController = navController,
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
                FABElement(navController = navController, currentScreen = currentScreen)
            }) { innerPadding ->
            NavGraph(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}
