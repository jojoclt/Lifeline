package com.example.lifeline.presentation.task.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lifeline.presentation.task.AddEditTodoEvent
import com.example.lifeline.presentation.task.AddEditTodoViewModel
import com.example.lifeline.util.toDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun DurationDrawer(
    durationValue: MutableState<Int>,
    bottomDrawerState: BottomDrawerState,
    viewModel: AddEditTodoViewModel,
    scope: CoroutineScope
) {
    val durationMinutes = listOf(15, 30, 45)
    val durationHours = listOf(1, 2, 3)

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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    Text(
                        text = "+ " + durationValue.value.toDuration(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(0.dp, 10.dp),
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
                    durationMinutes.forEach() {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp),
                            onClick = { durationValue.value += it }
                        ) {
                            Text(text = "+$it min")
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    durationHours.forEach {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp),
                            onClick = { durationValue.value += it * 60 }
                        ) {
                            Text(text = "+$it hour")
                        }
                    }
                }

                Spacer(modifier = Modifier.size(10.dp))
                Divider(thickness = 2.dp)
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp),
                        onClick = { durationValue.value = 0 }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "ResetDuration"
                        )
                    }
                    Button(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 10.dp),
                        onClick = {
                            viewModel.onEvent(
                                AddEditTodoEvent.EnteredDuration(
                                    durationValue.value
                                )
                            )
                            scope.launch {
                                bottomDrawerState.close()
                            }
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}