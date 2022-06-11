package com.example.lifeline.presentation.today

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.data.local.dummy
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.priorityList
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.today.composables.CupCanvas
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.presentation.ui.theme.textBoxBg
import com.example.lifeline.util.Screen
import java.time.LocalTime


@Composable
fun TodayScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.TodayScreen

    Scaffold(
        topBar = { TopNav(currentScreen) },
//        bottomBar = {
//            BottomNav(
//                navController = navController,
//                currentScreen = currentScreen,
//                modifier = Modifier.clip(
//                    shape = RoundedCornerShape(
//                        topStart = 20.dp,
//                        topEnd = 20.dp
//                    )
//                )
//            )
//        },
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estimated Time to Completed"
            )
            TimeRemain()
            CupCanvas(0.7f)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "To dos")
                Text(
                    text = "See More >",
                    modifier = Modifier.clickable { navController.navigate(Screen.TodosScreen.route) })
            }
            Surface(
                shape = MaterialTheme.shapes.large,
                elevation = 3.dp,
                color = textBoxBg,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(190.dp)
            ) {
                SampleTaskList(dummy, navController)
            }
        }
    }
}


@Composable

fun SampleTaskList(tasks: List<TaskData> = dummy, navController: NavController) {
//    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.size(20.dp))
//        }
//        items(tasks) { task ->
//           TaskCard(task)
//        }
//    }
    Column(modifier = Modifier.fillMaxHeight()) {
        for (i in 0..2)
            SampleTaskCard(tasks[i], navController)


//        SampleTaskCard(tasks[3])
//        SampleTaskCard(tasks[4])
    }
}

@Composable
fun SampleTaskCard(t: TaskData, navController: NavController) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
            .clickable { navController.navigate(Screen.AddTodoScreen.route + "?noteId=${t.id}&noteName=${t.taskName}") }
    ) {
        val isChecked = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .fillMaxHeight()
                        .clip(RectangleShape)
                        .background(Color.Red)

                )
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                    },
                    colors = CheckboxDefaults.colors(Color.Blue),
                )
                Text(text = t.taskName)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "2hr", modifier = Modifier.offset(x = (-2).dp, y = 0.dp))
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(horizontal = 6.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(priorityList[t.priority.ordinal]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f)
                    )
                }
            }
        }

    }
}

@Composable
fun TimeRemain() {
    val curTime = LocalTime.now()

    var remainingHour = 24 - curTime.hour
    var remainingMin = 60 - curTime.minute

    LaunchedEffect(key1 = remainingHour, key2 = remainingMin) {
        remainingHour = 24 - curTime.hour
        remainingMin = 60 - curTime.minute
        /*
        if(remainingMin >= 0) {
            delay(200L)
            remainingMin -= 1
            //value = remainingHour/TotalHour.toFloat()
        }

        else{
            remainingMin = 59
            remainingHour -= 1
        }
        if(remainingHour <= 0){
            remainingHour = 24
        }*/
    }
    Row() {
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


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Preview
@Composable
fun TodayScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = Screen.TodayScreen
    LifelineTheme {
        Scaffold(
            content = { TodayScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController)
            }
        )
    }
}