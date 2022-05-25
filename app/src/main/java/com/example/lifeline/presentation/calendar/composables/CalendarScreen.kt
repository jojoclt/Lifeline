package com.example.lifeline.presentation.calendar.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.lifeline.R

@Composable
fun CalendarScreen(navController: NavController) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.open_1),
        contentDescription = null,
        alignment = Alignment.TopEnd
    )
}