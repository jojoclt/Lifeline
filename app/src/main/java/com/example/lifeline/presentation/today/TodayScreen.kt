package com.example.lifeline.presentation.today

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.data.local.SampleList
import com.example.lifeline.data.local.SampleTask
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.today.composables.CupCanvas
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen
import kotlinx.coroutines.delay


@Composable
fun TodayScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.TodayScreen

    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Estimated Time to Completed"
            )
            TimeRemain()
            CupCanvas()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "To dos")
                Text(text = "See More >", modifier = Modifier.clickable { })
            }
            Surface(
                shape = MaterialTheme.shapes.large,
                elevation = 3.dp,
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                TaskList(SampleList.simpletasklist)
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<SampleTask>) {
    LazyColumn {
//        item {
//            Spacer(modifier = Modifier.size(20.dp))
//        }
        items(tasks) { task ->
            TaskCard(task)
        }
    }
}

@Preview
@Composable
fun TaskCard(t: SampleTask = SampleList.simpletasklist[0]) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp).padding(8.dp)
    ) {
        val isChecked = remember { mutableStateOf(false) }
        ConstraintLayout(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            val (label, button, text, time, ico) = createRefs()
            Box(
                modifier = Modifier.width(10.dp).fillMaxHeight().clip(RectangleShape).background(Color.Red).constrainAs(label) {
                    start.linkTo(parent.start)
                    end.linkTo(button.start)
                }
            )
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
                colors = CheckboxDefaults.colors(Color.Blue),
                modifier = Modifier.constrainAs(button) {
                    start.linkTo(label.start)
                    end.linkTo(text.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Text(text = t.name, modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(button.end)
                width = Dimension.fillToConstraints
            })
//            when (t.type) {
//                "COFFEE" -> {
//                    Text(text = "2hr")
//                    Image(
//                        imageVector = ImageVector.vectorResource(id = R.drawable.p_coffee),
//                        contentDescription = null
//                    )
//                }
//                "MILK" -> {
//                    Text(text = "1hr")
//                    Image(
//                        imageVector = ImageVector.vectorResource(id = R.drawable.p_milk),
//                        contentDescription = null
//                    )
//                }
//                "ICE" -> {
//                    Text(text = "30min")
//                    Image(
//                        imageVector = ImageVector.vectorResource(id = R.drawable.p_ice),
//                        contentDescription = null
//                    )
//                }
//            }

        }
    }
}

@Composable
fun CheckBoxDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Color Check Box")
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            val isChecked = remember { mutableStateOf(false) }
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                },
                colors = CheckboxDefaults.colors(Color.Red)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text("Apple")
        }
    }
}

@Composable
fun TimeRemain() {


    var remainingHour by remember {
        mutableStateOf(24L)
    }
    var remainingMin by remember {
        mutableStateOf(0L)
    }
    LaunchedEffect(key1 = remainingHour, key2 = remainingMin) {
        if (remainingMin >= 0) {
            delay(200L)
            remainingMin -= 1L
            //value = remainingHour/TotalHour.toFloat()
        } else {
            remainingMin = 59L
            remainingHour -= 1L
        }
        if (remainingHour <= 0) {
            remainingHour = 24L
        }
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
@Preview
@Composable
fun TodayScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = Screen.TodayScreen
    LifelineTheme {
        Scaffold(
            content = { TodayScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        )
    }
}