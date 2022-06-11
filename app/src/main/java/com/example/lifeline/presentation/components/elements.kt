package com.example.lifeline.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lifeline.R
import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.PriorityRes
import com.example.lifeline.presentation.task.AddEditTodoEvent
import com.example.lifeline.presentation.task.AddEditTodoViewModel
import com.example.lifeline.presentation.ui.theme.PrimaryColor
import com.example.lifeline.presentation.ui.theme.SelectorColour

var items = listOf(
    PriorityRes(R.drawable.p_coffee, Priority.ESPRESSO),
    PriorityRes(R.drawable.p_milk, Priority.MILK),
    PriorityRes(R.drawable.p_ice, Priority.ICE)
)


@Composable
fun PrioritySelector(viewModel: AddEditTodoViewModel) {
    val selectedValue = remember {
        mutableStateOf(Priority.ESPRESSO)
    }
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable(onClick = {
                        viewModel.onEvent(AddEditTodoEvent.EnteredPriority(item.priority))
                        selectedValue.value = item.priority
                    })
                    .background(if (selectedValue.value == item.priority) SelectorColour else PrimaryColor)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = item.img),
                    contentDescription = ""
                )
            }
        }
    }
}
