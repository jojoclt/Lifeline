package com.example.lifeline.presentation.task.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import com.example.lifeline.domain.model.priorityList
import com.example.lifeline.presentation.today.TodayEvent
import com.example.lifeline.presentation.today.TodayViewModel
import com.example.lifeline.presentation.ui.theme.DeadlineColor
import com.example.lifeline.presentation.ui.theme.TodoColor
import com.example.lifeline.util.Screen
import com.example.lifeline.util.toDurationInList

@Composable
fun TasksList(viewModel: TodayViewModel, navController: NavController, amount: Int = 0) {
    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.size(20.dp))
//        }

        if (amount == 0)
            items(viewModel.state.value.tasks) { task ->
                TaskCard(task, navController, viewModel)
            }
        else
            items(viewModel.state.value.tasks.take(amount)) { task ->
                TaskCard(task, navController, viewModel)
            }
    }
}

@Composable
fun TaskCard(t: TaskData, navController: NavController, viewModel: TodayViewModel) {
    val checkedStatus = remember { mutableStateOf(t.isChecked) }

    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
            .clickable {
                if (t.taskType == TaskType.TODO) navController.navigate(Screen.AddTodoScreen.route + "?taskId=${t.id}")
                else navController.navigate(Screen.AddDeadlineScreen.route + "?taskId=${t.id}")
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
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
                    checked = checkedStatus.value,
                    onCheckedChange = {
                        viewModel.onEvent(TodayEvent.ToggleButton(it, t))
                        checkedStatus.value = it


                    },
                    colors = CheckboxDefaults.colors(Color.Blue),
                )
                Text(text = t.taskName)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                val str = if (t.taskType == TaskType.TODO) t.duration.toDurationInList() else t.time
                Text(
                    text = str,
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