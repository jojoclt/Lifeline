package com.example.lifeline.presentation.today.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen



@Composable
fun TodayScreen(navController: NavController) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Estimated Time to Completed"
        )
        CupCanvas()
        Row(){
            Text( text = "To dos") //later make it be on left
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "See More") //later make it be on right
        }
        LazyColumn(

        ){
            item{Text(text = "wait for data")}
        }
    }
}

@Composable
fun CupCanvas() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(screenWidth)
    ) {
        drawRect(
            color = Color.Black,
            size = size,
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun TodayScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = Screen.TodayScreen
    LifelineTheme {
        Scaffold(
            topBar = { TopNav(currentScreen) },
            content = { TodayScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        )
    }
}