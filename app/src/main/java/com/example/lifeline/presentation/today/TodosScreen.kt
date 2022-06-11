package com.example.lifeline.presentation.today

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lifeline.data.local.dummy
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.task.composables.TasksList
import com.example.lifeline.presentation.today.composables.CupCanvas
import com.example.lifeline.util.Screen

@Composable
fun TodosScreen(navController: NavController) {
    val currentScreen = Screen.TodosScreen

    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Column() {
            Card(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .padding(20.dp), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CupCanvas(0.4f)
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(2f)
                    ) {
                        Text("Est. time to complete")
                        Text("4 hr 0 min")
                    }
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            TasksList(dummy)

        }
    }
}