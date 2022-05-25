package com.example.lifeline.presentation.home.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.open_2),
        contentDescription = null,
        alignment = Alignment.TopEnd
    )
}
