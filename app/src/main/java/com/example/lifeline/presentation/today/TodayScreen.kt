package com.example.lifeline.presentation.today

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.data.local.dummy
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import com.example.lifeline.domain.model.priorityList
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.task.composables.TasksList
import com.example.lifeline.presentation.today.composables.CupCanvas
import com.example.lifeline.presentation.ui.theme.DeadlineColor
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.presentation.ui.theme.TodoColor
import com.example.lifeline.presentation.ui.theme.textBoxBg
import com.example.lifeline.util.Screen
import com.example.lifeline.util.toDurationInList
import java.lang.Integer.min
import java.time.LocalTime

@Composable
fun TodayScreen(navController: NavController, viewModel: TodayViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.TodayScreen

    val taskList = viewModel.getTodoTask()
    val time = viewModel.getDuration()

    Scaffold(
        topBar = { TopNav(currentScreen) },

        ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estimated Time to Completed"
            )
//            TimeRemain()
            Text(
                text = time.toDurationInList(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            CupCanvas(0.7f, taskList)
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
                TasksList(taskList, navController, 3)
            }
        }
    }
}


@Composable

fun _TaskList(tasks: List<TaskData> = dummy, navController: NavController) {
//    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.size(20.dp))
//        }
//        items(tasks) { task ->
//           TaskCard(task)
//        }
//    }
    Column(modifier = Modifier.fillMaxHeight()) {
        if (tasks.isNotEmpty())
            for (i in 0..min(2, tasks.size - 1))
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
            .clickable {
                Log.d("TodayScreen", "taskID: ${t.id}")
                if (t.taskType == TaskType.TODO) navController.navigate(Screen.AddTodoScreen.route + "?taskId=${t.id}")
                else navController.navigate(Screen.AddDeadlineScreen.route + "?taskId=${t.id}")
            }
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
                        .background(if (t.taskType == TaskType.TODO) TodoColor else DeadlineColor)

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
                Text(
                    text = t.duration.toDurationInList(),
                    modifier = Modifier.offset(x = (-2).dp, y = 0.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(horizontal = 6.dp)
                ) {
                    if (t.taskType == TaskType.TODO)
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

    var remainingHour = 24 - curTime.hour-1
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
            text = "${remainingHour}hr${remainingMin}min",
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
                    navController = navController
                )
            }
        )
    }
}