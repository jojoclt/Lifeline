package com.example.lifeline.presentation.today

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.task.composables.TasksList
import com.example.lifeline.presentation.today.composables.CupCanvas
import com.example.lifeline.util.Screen
import com.example.lifeline.util.toDurationInList

@Composable
fun TodosScreen(navController: NavController, viewModel: TodayViewModel = hiltViewModel()) {
    val currentScreen = Screen.TodosScreen
    val taskList = viewModel.getTodoTask()
    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Column(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier.padding(20.dp), shape = RoundedCornerShape(40.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .padding(20.dp), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CupCanvas(0.4f, viewModel.state.value.tasks)
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(2f)
                    ) {
                        Text("Est. time to complete")
                        Text(viewModel.getDuration().toDurationInList(), fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red)
                    }
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            TasksList(taskList, navController)

        }
    }
}