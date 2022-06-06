package com.example.lifeline.presentation.components

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.lifeline.R

@Preview
@Composable
fun PrioritySelector() {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        PriorityElement()
        PriorityElement()
        PriorityElement()
    }
}

@Preview
@Composable
fun PriorityElement(@DrawableRes img: Int = R.drawable.p_coffee) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.Red)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Image(painter = painterResource(id = img), contentDescription = "")
    }
}