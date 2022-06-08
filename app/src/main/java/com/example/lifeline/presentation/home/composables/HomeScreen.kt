package com.example.lifeline.presentation.home.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeline.R
import com.example.lifeline.presentation.BottomNav
import com.example.lifeline.presentation.TopNav
import com.example.lifeline.presentation.ui.theme.LifelineTheme
import com.example.lifeline.util.Screen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val currentScreen = Screen.HomeScreen


//    Scaffold(
//        topBar = { TopNav(currentScreen)}
//    )
    Scaffold(
        topBar = { TopNav(currentScreen = currentScreen)}
    ) { contentPadding ->
        //val padding = 40.dp
        val vector = ImageVector.vectorResource(id = R.drawable.sun)
        val painter = rememberVectorPainter(image = vector)
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
//            Image(
//                imageVector = ImageVector.vectorResource(id = R.drawable.sun),
//                contentDescription = null,
//                modifier = Modifier.scale(2f)
//            )
            Canvas(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {

                inset (horizontal = -0.7f * size.width, vertical = -0.32f * size.height) {
                    with(painter) {
                        draw(painter.intrinsicSize)
                    }
                }
            }
            Column(
                //modifier = Modifier
                    //.fillMaxHeight()
                    //.fillMaxWidth()
            ) {
                Text(text = "show")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(50.dp)
                        .fillMaxWidth()
                    //.size(width = 360.dp, height = 100.dp)
                    //.fillMaxWidth()
                ) {
//                    Text(text = "Text1")
//                    Text(text="Text2")
//                    Text("end")
                        WeatherCard(weather = "Sunny", -1)
                        Spacer(modifier = Modifier
                            .padding(5.dp))
                        WeatherCard(weather = "Rainy", 0)
                        Spacer(modifier = Modifier
                            .padding(5.dp))
                        WeatherCard(weather = "Rainy", 1)
                        Spacer(modifier = Modifier
                            .padding(5.dp))
                }
//                Image(
//                    painter = painterResource(id = R.drawable.avatar),
//                    contentDescription = null,
//                    modifier = Modifier
//                        //.align(Alignment.Center)
//                        .padding(top = 180.dp, start = 70.dp)
//                        .scale(2f)
//                )
                Text(text = "hi")
                Text(text = "lol")

            }

        }

//        Box(modifier = Modifier.padding(contentPadding)){
//            Image(
//                imageVector = ImageVector.vectorResource(id = R.drawable.camp),
//                contentDescription = null)
//            Image(
//                painter = painterResource(id = R.drawable.avatar),
//                contentDescription = null)
//        }

    }
    //old
//    Scaffold(topBar = { TopNav(currentScreen) }) { _ ->
//
//        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
//            Image(
//                imageVector = ImageVector.vectorResource(id = R.drawable.open_3),
//                contentDescription = null,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//    }
}

@Composable
fun WeatherCard(
    //val Weather = 0
    weather:String,
    delta:Int,
//    modifier: Modifier = Modifier
//        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
){
    //icon, text, time
    Box(
        modifier = Modifier
            .size(70.dp, 100.dp)
            .background(colorResource(id = R.color.teal_700))
            //.padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_forecast),
                modifier = Modifier
                    .fillMaxWidth(),
                contentDescription = null)
            Text(text = weather)
            val str:String = getTime(delta)

            Text(str)
        }
    }
}

fun getTime(
    delta:Int
): String {
//    val tsLong = System.currentTimeMillis() / 1000
//    val secLong = tsLong/1000
    //String ts = tsLong.toString();
    if (delta == 0) return "NOW"
    val result = Calendar.getInstance()
    result.add(Calendar.HOUR_OF_DAY,delta)
    //val result1= Calendar.HOUR + delta
    //val result2 = Calendar.MINUTE
    val dateFormat = SimpleDateFormat("HH:mm")
//    val str:String = "${result1}:${result2}"
    val ts = dateFormat.format(result.time)

    return ts
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
                    navController = navController,
                    currentScreen = currentScreen
                )
            }
        )
    }
}