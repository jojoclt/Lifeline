package com.example.lifeline.presentation.task.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
    var taskData = viewModel.state.value.tasks
    if (amount > 0) taskData = taskData.take(amount)

    LazyColumn {
        items(taskData) { task ->
            TaskCard(task, navController) { checkedStatus, id ->
                viewModel.onEvent(TodayEvent.ToggleButton(checkedStatus, id))
            }
        }
    }
}

@Composable
fun TaskCard(
    taskData: TaskData,
    navController: NavController,
    onCheckedChange: (Boolean, Int) -> Unit
) {
    var checkedState by remember { mutableStateOf(taskData.isChecked) }
    TaskItem(
        taskData = taskData,
        navController = navController,
        checked = checkedState
    ) { boolean, id ->
        onCheckedChange(boolean, id)
        checkedState = boolean
    }
}

@Composable
fun TaskItem(
    taskData: TaskData,
    navController: NavController,
    checked: Boolean,
    onCheckedChange: (Boolean, Int) -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
            .clickable {
                if (taskData.taskType == TaskType.TODO) navController.navigate(Screen.AddTodoScreen.route + "?taskId=${taskData.id}")
                else navController.navigate(Screen.AddDeadlineScreen.route + "?taskId=${taskData.id}")
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
                        .background(if (taskData.taskType == TaskType.TODO) TodoColor else DeadlineColor)

                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        onCheckedChange(it, taskData.id!!)
                    },
                    colors = CheckboxDefaults.colors(Color.Blue),
                )
                Text(text = taskData.taskName)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                val str =
                    if (taskData.taskType == TaskType.TODO) taskData.duration.toDurationInList() else taskData.time
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
                    if (taskData.taskType == TaskType.TODO)
                        Image(
                            imageVector = ImageVector.vectorResource(priorityList[taskData.priority.ordinal]),
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