package com.example.lifeline.presentation.today.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.data.local.SampleList
import com.example.lifeline.data.local.SampleTask
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen
import kotlinx.coroutines.delay


@Composable
fun TodayScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.TodayScreen
    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estimated Time to Completed"
            )
            TimeRemain()
            CupCanvas()
            Row() {
                Text(text = "To dos") //later make it be on left
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "See More") //later make it be on right
            }
            Surface(
                shape = MaterialTheme.shapes.large,
                elevation = 3.dp,
                color = Color.LightGray,
            ) {
            TaskList(SampleList.simpletasklist)
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<SampleTask>){
    LazyColumn{
        items(tasks){task->TaskCard(task)}
    }
}

@Composable
fun TaskCard(t: SampleTask){
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 1.dp,
    ) {
        Text( text = t.detail)
    }
}

@Composable
fun TimeRemain(){
    var remainingHour by remember {
        mutableStateOf(24L)
    }
    var remainingMin by remember {
        mutableStateOf(0L)
    }
    LaunchedEffect(key1 = remainingHour, key2 = remainingMin){
        if(remainingMin >= 0) {
            delay(200L)
            remainingMin -= 1L
            //value = remainingHour/TotalHour.toFloat()
        }
        else{
            remainingMin = 59L
            remainingHour -= 1L
        }
        if(remainingHour <= 0){
            remainingHour = 24L
        }
    }
    Row(){
        Text(
            text = remainingHour.toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "hr",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = remainingMin.toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "min",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
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