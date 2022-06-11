package com.example.lifeline.presentation.task.composables

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.components.DatePicker
import com.example.lifeline.presentation.components.PrioritySelector
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.presentation.ui.theme.Shapes
import com.example.lifeline.presentation.ui.theme.myAppTextFieldColors
import com.example.lifeline.util.Screen
import com.example.lifeline.util.clearFocusOnKeyboardDismiss
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun AddTodoScreen(navController: NavController, viewModel: AddEditTodoViewModel = hiltViewModel()) {
    val currentScreen = Screen.AddTodoScreen

    val task = viewModel.taskEntry
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var showPicker by rememberSaveable { mutableStateOf(false) }
    var date by rememberSaveable { mutableStateOf(false) }

    val selDate = remember { mutableStateOf(Calendar.getInstance().time) }

    val bottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopNav(currentScreen, modifier = Modifier.background(Color.White)) },
        backgroundColor = Color.White
    ) { innerPadding ->
        BottomDrawer(
            drawerState = bottomDrawerState,
            drawerContent = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    shape = RoundedCornerShape(20.dp)
                )
                {
                    Box(modifier = Modifier.padding(20.dp)) {
                        Column(
                            modifier = Modifier.fillMaxHeight()
                        )
                        {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                            )
                            {
                                Text(
                                    text = "00:00",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .offset(0.dp, 10.dp),
                                    letterSpacing = 15.sp,
                                    fontSize = 60.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "+30 min")
                                }

                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "+1 hr")
                                }

                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "+2 hr")
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "+30 min")
                                }

                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "+1 hr")
                                }

                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "+2 hr")
                                }
                            }

                            Spacer(modifier = Modifier.size(10.dp))
                            Divider(thickness = 2.dp)
                            Spacer (modifier = Modifier.size(10.dp))
                            Row(modifier = Modifier.
                                fillMaxWidth()
                            ) {
                                Button(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text ="R") /* Todo change into icon */
                                }
                                Button(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(horizontal = 10.dp),
                                    onClick = {}
                                ) {
                                    Text(text = "Save")
                                }
                            }
                        }
                    }
                }

            },
            content = {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()

                )
                {
                    Column {
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .height(100.dp)
                        ) {
                            TextField(
                                value = task.value.taskName,
                                onValueChange = { viewModel.onEvent(AddEditTodoEvent.EnteredTitle(it)) },
                                colors = myAppTextFieldColors(),
                                shape = Shapes.large,
                                label = { Text(stringResource(R.string.task_name)) },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),

                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                singleLine = true,
                                modifier = Modifier
                                    .clearFocusOnKeyboardDismiss()
                                    .fillMaxWidth()
                            )


                        }
                        Divider(thickness = 2.dp)
                        Row(modifier = Modifier.padding(vertical = 20.dp)) {
                            /* TODO add duration */
                            if (showPicker)
                                DatePicker(onDismissRequest = {
                                    showPicker = false
                                }, viewModel = viewModel)

                            /* for calendar format  */
                            val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
                            val strDate = dateFormat.format(viewModel.taskEntry.value.date)
                            TextField(
                                value = strDate,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "DateRange"
                                    )
                                },
                                onValueChange = {},
                                colors = myAppTextFieldColors(),
                                modifier = Modifier
                                    .requiredWidth(212.dp)
                                    .padding(horizontal = 20.dp)
                                    .clickable(onClick = { showPicker = true }),

                                enabled = false
                            )

                            Button(
                                onClick = { scope.launch { bottomDrawerState.open() } }
                            ) {
                                Text("Duration")
                            }

                        }
                        Log.e("todo", "after show picker")

                        Divider(thickness = 2.dp)
                        Box(modifier = Modifier.padding(20.dp)) {
                            PrioritySelector(viewModel)
                        }
                        Divider(thickness = 2.dp)
                        Box(modifier = Modifier.padding(20.dp)) {
                            TextField(
                                value = task.value.desc,
                                onValueChange = { viewModel.onEvent(AddEditTodoEvent.EnteredDescription(it)) },
                                colors = myAppTextFieldColors(),
                                shape = Shapes.large,
                                label = { Text(stringResource(R.string.description)) },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),

                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                modifier = Modifier
                                    .clearFocusOnKeyboardDismiss()
                                    .fillMaxWidth()
                                    .height(120.dp)
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, false)
                            .padding(innerPadding)
                    ) {
                        Button(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
                            Text(text = "Cancel")
                        }
                        Button(onClick = {
                            viewModel.onEvent(AddEditTodoEvent.SaveNote)
                            Toast.makeText(
                                context,
                                "Task Added",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Screen.HomeScreen.route)
                        }) {
                            Text(text = "DONE")
                        }
                    }

                }


            }
        )

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun AddTodoScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = Screen.AddTodoScreen
    LifelineTheme {
        Scaffold(
            content = { AddTodoScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        )
    }
}
