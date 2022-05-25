package com.example.lifeline.presentation.today.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.lifeline.R

@Composable
fun TodayScreen(navController: NavController) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.open_3),
        contentDescription = null,
        alignment = Alignment.TopEnd
    )
}