package com.example.lifeline.presentation.home.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.LifelineApp
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen

@Composable
fun HomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.HomeScreen


    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.open_2),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = Screen.HomeScreen
    LifelineTheme {
        Scaffold(
            content = { HomeScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        )
    }
}