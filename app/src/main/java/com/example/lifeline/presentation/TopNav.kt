package com.example.lifeline.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.task.AddEditTodoEvent
import com.example.lifeline.presentation.task.AddEditTodoViewModel
import com.example.lifeline.util.AddTaskItems
import com.example.lifeline.util.Screen

@Composable
fun TopNav(
    currentScreen: Screen,
    modifier: Modifier = Modifier,
    viewModel: AddEditTodoViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
    text: String? = null
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = text ?: stringResource(id = currentScreen.resourceId)
            )
        },
        actions = {
            if (viewModel.bool.value) {
                EditTaskAction(viewModel, navController, context)
            } else
                when (currentScreen) {
//                Screen.CalendarScreen -> CalendarScreenAction(action)
                    in AddTaskItems -> {}
                    else -> NormalScreenAction()
                }
        },

        modifier = modifier,
        elevation = 0.dp,

        )

}

@Composable
fun NormalScreenAction() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painterResource(id = R.drawable.ic_help),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            Icons.Filled.Settings,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun CalendarScreenAction(action: () -> Unit) {
    IconButton(onClick = action) {
        Icon(
            painterResource(id = R.drawable.ic_today),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
    NormalScreenAction()
}

@Composable
fun EditTaskAction(
    viewModel: AddEditTodoViewModel,
    navController: NavController,
    context: Context
) {
    IconButton(onClick = {
        viewModel.onEvent(AddEditTodoEvent.DeleteNote)
        Toast.makeText(
            context,
            "Task Deleted",
            Toast.LENGTH_SHORT
        ).show()
        navController.navigateUp()
    }) {
        Icon(
            painterResource(id = R.drawable.ic_delete),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}