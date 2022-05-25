package com.example.lifeline.presentation.home.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.LifelineApp
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.open_2),
        contentDescription = null,
        alignment = Alignment.TopEnd
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun HomeScreenPreview() {
    LifelineTheme() {
        Scaffold(
            topBar = { TopNav(title = R.string.home_screen) },
            content = { HomeScreen() },
            bottomBar = { BottomNav() }
        )
    }
}