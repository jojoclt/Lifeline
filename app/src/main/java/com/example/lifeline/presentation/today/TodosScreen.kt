package com.example.lifeline.presentation.today



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.lifeline.R
import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.components.items
import com.example.lifeline.util.Screen

@Composable
fun TodosScreen(navController: NavController){
    val currentScreen = Screen.TodosScreen
    
    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Column() {

        }
    }
}

@Composable
fun TodayTaskList(tasks: List<TaskData>){
    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.size(20.dp))
//        }
        items(tasks) { task ->
            TodayTaskCard(task)
        }
    }
}

@Composable
fun TodayTaskCard(task: TaskData){
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
    ) {
        val isChecked = remember { mutableStateOf(false) }
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            val (label, button, text, time, ico) = createRefs()
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .fillMaxHeight()
                    .clip(RectangleShape)
                    .background(Color.Red)
                    .constrainAs(label) {
                        start.linkTo(parent.start)
                        end.linkTo(button.start)
                    }
            )
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                    task.isChecked = it
                },
                colors = CheckboxDefaults.colors(Color.Blue),
                modifier = Modifier.constrainAs(button) {
                    start.linkTo(label.start)
                    end.linkTo(text.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Text(text = task.taskName,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(button.end)
                    width = Dimension.fillToConstraints
                })
            when (task.priority) {
                Priority.ESPRESSO -> {
                    Text(text = "2hr",
                        modifier = Modifier.constrainAs(time){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            //start.linkTo(text.end)
                            end.linkTo(ico.start)

                        })
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.p_coffee),
                        contentDescription = null,
                        modifier = Modifier.constrainAs(ico){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            //start.linkTo(time.end)
                            end.linkTo(parent.absoluteRight)
                        },
                    )
                }
                Priority.MILK -> {
                    Text(text = "1hr",
                        modifier = Modifier.constrainAs(time){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(ico.start)
                        })
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.p_milk),
                        contentDescription = null,
                        modifier = Modifier.constrainAs(ico){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.absoluteRight)
                        }
                    )
                }
                Priority.ICE -> {
                    Text(text = "30min",
                        modifier = Modifier.constrainAs(time){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(ico.start)
                        })
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.p_ice),
                        contentDescription = null,
                        modifier = Modifier.constrainAs(ico){
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.absoluteRight)
                        }
                    )
                }
            }

        }
    }
}