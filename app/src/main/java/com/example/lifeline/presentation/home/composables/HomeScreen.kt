package com.example.lifeline.presentation.home.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.today.TodayViewModel
import com.example.lifeline.presentation.ui.theme.*
import com.example.lifeline.util.Screen
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: TodayViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.HomeScreen

    Scaffold(
        topBar = { TopNav(currentScreen = currentScreen) },

    ) { _ ->

        /**
         * weather[0] denotes the previous, now and next
         */
        val weather = listOf("Rainy", "Thunder", "Sunny")

        /* TODO : Change the drawable depending on the weather */
        var vector = when(weather[1]) {
            "Sunny" -> ImageVector.vectorResource(id = R.drawable.weather_sunny)
            "Windy" -> ImageVector.vectorResource(id = R.drawable.weather_windy)
            "Rainy" -> ImageVector.vectorResource(id = R.drawable.weather_rainy)
            "Thunder" -> ImageVector.vectorResource(id = R.drawable.weather_thunder)
            else -> ImageVector.vectorResource(id = R.drawable.weather_sunny)
        }
        if (viewModel.getTasks().isEmpty()) {
            vector = ImageVector.vectorResource(id = R.drawable.camp)
        }

        /* set the background to camp when there is no task */

        val painter = rememberVectorPainter(image = vector)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
//                horizontal = -0.7f * size.width, vertical = -0.32f * size.height
                inset(horizontal = -0.7f * size.width, vertical = -0.42f * size.height) {
                    with(painter) {
                        draw(painter.intrinsicSize)
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (vector == ImageVector.vectorResource(id = R.drawable.camp)) {
                    Log.e("HomeScreen", "print weather card")
                    WeatherCard(
                        weather = weather[0],
                        delta = -1,
                        offset = 100.dp,
                        width = 90.dp,
                        height = 160.dp,
                        roundedDp = 70.dp,
                        cardColor = CardColor,
                        textColor = textColor
                    )
                    Spacer(
                        modifier = Modifier
                            .size(10.dp)
                    )
                    WeatherCard(
                        weather = weather[1],
                        delta = 0,
                        offset = 60.dp,
                        width = 100.dp,
                        height = 180.dp,
                        roundedDp = 110.dp,
                        cardColor = CardColorSelected,
                        textColor = textBoxBg
                    )
                    Spacer(
                        modifier = Modifier
                            .size(10.dp)
                    )
                    WeatherCard(
                        weather = weather[2],
                        delta = 1,
                        offset = 100.dp,
                        width = 90.dp,
                        height = 160.dp,
                        roundedDp = 70.dp,
                        cardColor = CardColor,
                        textColor = textColor
                    )
                } else {
                    /* TODO : Show dialog box you haven't planned for today */
                    Log.e("HomeScreen", "doesn't print weather card")

                }
            }
        }
    }
}

@Composable
fun WeatherCard(
    weather: String,
    delta: Int,
    offset: Dp,
    width: Dp,
    height: Dp,
    roundedDp: Dp,
    cardColor: Color,
    textColor: Color
) {
    /**
     * fetch icon from weather name
     */
    val icon = when(weather) {
        "Sunny" -> ImageVector.vectorResource(id = R.drawable.ic_sun)
        "Rainy" -> ImageVector.vectorResource(id = R.drawable.ic_rain)
        "Windy" -> ImageVector.vectorResource(id = R.drawable.ic_wind)
        "Thunder" -> ImageVector.vectorResource(id = R.drawable.ic_thunder)
        else -> ImageVector.vectorResource(id = R.drawable.ic_alarm)
    }
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(roundedDp),
        modifier = Modifier
            .size(width, height)
            .offset(y = offset)
            .alpha(0.9f),

        backgroundColor = cardColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                imageVector = icon,
                modifier = Modifier
                    .fillMaxWidth(),
                contentDescription = "weatherIcon"
                //colorFilter = ColorFilter.tint(textColor)
            )
            Text(
                color = textColor,
                text = weather,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                color = textColor,
                text = getWeatherTime(delta),
                fontSize = 20.sp,
            )
        }
    }
}

/**
 * function to get the time in the weather card
 */
fun getWeatherTime(
    delta: Int
): String {

    val currentTime = Calendar.getInstance()
    currentTime.add(Calendar.HOUR_OF_DAY, delta)

    val dateFormat = SimpleDateFormat("HH:mm", Locale.US)
    return if (delta == 0) "Now"
    else dateFormat.format(currentTime.time)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val currentScreen = Screen.HomeScreen
    LifelineTheme {
        Scaffold(
            content = { HomeScreen(navController) },
            bottomBar = {
                BottomNav(
                    navController = navController)
            }
        )
    }
}
