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
import com.example.lifeline.domain.model.priorityList
import com.example.lifeline.util.Screen

@Composable
fun TasksList(tasks: List<TaskData>, navController: NavController) {
    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.size(20.dp))
//        }
        items(tasks) { task ->
            TaskCard(task, navController)
        }
    }
}

@Composable
fun TaskCard(t: TaskData, navController: NavController) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp).clickable {
                navController.navigate(Screen.AddTodoScreen.route + "?taskId=${t.id}") }
    ) {
        val isChecked = remember { mutableStateOf(false) }

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
                Text(text = t.duration.toString(), modifier = Modifier.offset(x = (-2).dp, y = 0.dp))
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