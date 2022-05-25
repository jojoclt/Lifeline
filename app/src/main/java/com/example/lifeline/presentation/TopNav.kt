package com.example.lifeline.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lifeline.R
import com.example.lifeline.util.Screen

@Composable
fun TopNav(
    modifier: Modifier = Modifier, title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        actions = {

            if (title == Screen.Calendar.name) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(id = R.drawable.ic_today),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painterResource(id = R.drawable.ic_help),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )

            }

        },
        elevation = 0.dp
    )

}