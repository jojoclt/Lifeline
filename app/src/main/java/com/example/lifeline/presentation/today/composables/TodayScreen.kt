package com.example.lifeline.presentation.today.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lifeline.R
import com.example.lifeline.presentation.TopNav

@Composable
fun TodayScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = { TopNav(title = R.string.today_screen) }) {

    }
}