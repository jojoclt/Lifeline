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
                TasksList(viewModel, navController, 3)
            }
        }
    }
}

