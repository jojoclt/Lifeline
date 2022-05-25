package com.example.lifeline.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lifeline.util.Screen

@Composable
fun BottomNav(
    allScreens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    currentScreen: Screen,
    modifier: Modifier = Modifier
) {
    BottomNavigation(backgroundColor = Color.White, elevation = 16.dp, modifier = modifier) {
        allScreens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(screen.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(screen.name) },
                onClick = { onTabSelected(screen) },
                selected = screen == currentScreen

            )
        }
    }
}