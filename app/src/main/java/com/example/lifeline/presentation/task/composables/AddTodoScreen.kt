package com.example.lifeline.presentation.task.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.lifeline.R

@Composable
fun AddTodoScreen() {
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_forecast),
            contentDescription = null,
        )
    }
}