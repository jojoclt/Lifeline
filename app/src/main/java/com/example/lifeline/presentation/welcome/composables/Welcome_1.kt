package com.example.lifeline.presentation.welcome.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lifeline.R
import com.example.lifeline.presentation.ui.theme.LifelineTheme

@Composable
@Preview
fun Welcome_1(modifier: Modifier = Modifier) {
    Scaffold() {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Box() {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.open_1),
                    contentDescription = null,
                    alignment = Alignment.TopEnd
                )
            }

        }

    }
}