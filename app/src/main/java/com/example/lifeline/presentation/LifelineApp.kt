package com.example.lifeline.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun LifelineApp() {
    val navController = rememberNavController()

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    LifelineTheme {
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
        Scaffold(
            topBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                when (val currentRoute = navBackStackEntry?.destination?.route) {
                    "home_screen" -> TopNav(title = R.string.home_screen)
                    "calendar_screen" -> TopNav(title = R.string.calendar_screen, isCalendar = true)
                    "today_screen" -> TopNav(title = R.string.today_screen)
                    else -> TopNav(title = R.string.app_name)
                }
                     },
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
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add, "", tint = Color.White)
                }
            }
        ) {
            NavGraph(navController)
        }
    }
}