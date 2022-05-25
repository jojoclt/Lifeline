package com.example.lifeline.presentation.today.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen

@Composable
fun TodayScreen(navController: NavController) {
    Column() {
        CupCanvas()
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.open_3),
            contentDescription = null,
            alignment = Alignment.TopEnd
        )
    }
}

@Composable
fun CupCanvas() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
    ) {
        drawRect(
            color = Color.Black,
            size = size,
        )
    }
}

@Preview
@Composable
fun TodayScreenPreview() {
    val navController = rememberNavController()
    val allScreens = Screen.values().toList()
    LifelineTheme {
        Scaffold(
            topBar = { TopNav(title = "Today") },
            content = { TodayScreen(navController) },
            bottomBar = {
                BottomNav(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = Screen.Today
                )
            }
        )
    }
}