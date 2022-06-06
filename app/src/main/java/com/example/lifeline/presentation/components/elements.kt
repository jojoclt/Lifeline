package com.example.lifeline.presentation.components

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        PriorityElement(R.drawable.p_coffee)
        PriorityElement(R.drawable.p_milk)
        PriorityElement(R.drawable.p_ice)
    }
}

@Preview
@Composable
fun PriorityElement(@DrawableRes img: Int = R.drawable.p_coffee) {
    val col = remember { mutableStateOf(Color.Red)}
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(col.value)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clickable (onClick = {col.value = Color.Blue})
    ) {
        Image(painter = painterResource(id = img), contentDescription = "")
    }
}