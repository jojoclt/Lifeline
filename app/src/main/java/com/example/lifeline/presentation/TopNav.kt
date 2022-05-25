package com.example.lifeline.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lifeline.R

@Composable
fun TopNav(
    modifier: Modifier = Modifier, @StringRes title: Int, isCalendar: Boolean = false,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title)
            )
        },
        actions = {


            if (isCalendar) {
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