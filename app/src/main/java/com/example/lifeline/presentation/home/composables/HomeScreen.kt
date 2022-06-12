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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

const val TAG = "HomeScreen"
@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: TodayViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.HomeScreen
    /**
     * weather[0] denotes the previous, now and next
     */
    val magicBoolean = viewModel.getTodoTask().isEmpty()

    Scaffold(
        topBar = { TopNav(currentScreen = currentScreen) },

    ) { _ ->
        Log.e(TAG, "start home screen")
        var weatherState by rememberSaveable { Log.e(TAG, "weather is updated"); mutableStateOf(if (magicBoolean) R.drawable.camp else R.drawable.weather_thunder) }
        val weather = listOf("Rainy", "Thunder", "Sunny")

        weatherState = if (magicBoolean) {
            Log.e(TAG, "it is empty")
            R.drawable.camp
        } else {
            R.drawable.weather_thunder
        }

//        if (viewModel.getTasks().isEmpty()) {
//            Log.e("HomeScreen", "")
//            isTaskEmpty = true
//        }
        /* TODO : Change the drawable depending on the weather */
//        lateinit var vector: ImageVector
//        if(!isTaskEmpty) {
//            vector = when (weather[1]) {
//                "Sunny" -> ImageVector.vectorResource(id = R.drawable.weather_sunny)
//                "Windy" -> ImageVector.vectorResource(id = R.drawable.weather_windy)
//                "Rainy" -> ImageVector.vectorResource(id = R.drawable.weather_rainy)
//                else -> ImageVector.vectorResource(id = R.drawable.weather_thunder)
//            }
//        } else {
//            vector = ImageVector.vectorResource(id = R.drawable.camp)
//        }
//        Image(
//            painterResource(id = weatherState),
//            contentDescription = "backgroundState",
//            modifier = Modifier.background(Color.Black).fillMaxSize()
//        )
        val painter = rememberVectorPainter(image = ImageVector.vectorResource(id = weatherState))

        /* set the background to camp when there is no task */
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
                /**
                 * They have 2 different modifiers to be able to have it fit to screen
                 */
                if (weatherState == R.drawable.camp) {
                    inset(horizontal = -0.64f * size.width, vertical = -0.47f * size.height) {
                        with(painter) {
                            draw(painter.intrinsicSize)
                        }
                    }
                } else {
                    inset(horizontal = -0.8f * size.width, vertical = -0.47f * size.height) {
                        with(painter) {
                            draw(painter.intrinsicSize)
                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
//                Button(onClick = {painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.camp))} ) {
//                    Text("E")
//                }
                if (!magicBoolean) {
                    Log.e(TAG, "print weather card")
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
                    Log.e(TAG, "doesn't print weather card")

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
