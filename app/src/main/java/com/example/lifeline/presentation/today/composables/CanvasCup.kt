package com.example.lifeline.presentation.today.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.example.lifeline.R
import com.example.lifeline.util.VectorHelper

// https://developer.android.com/reference/android/graphics/BlendMode.html#SRC
// https://stackoverflow.com/questions/65653560/jetpack-compose-applying-porterduffmode-to-image BUG
@Composable
//@Preview
fun CupCanvas() {
    val context = LocalContext.current

    val mask = VectorHelper.createPainter(img = R.drawable.coffeemask)
    val cup = VectorHelper.createPainter(img = R.drawable.coffee_empty)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    Box(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight(0.5f)
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            drawOval(
                color = Color.LightGray,
                size = Size(size.width, size.height * 0.3f),
                topLeft = Offset(x = 0f, y = 0.7f * size.height)
            )
            with(cup) {
                translate(left = size.width / 18f, block = { draw(size) })
            }
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            with(drawContext.canvas.nativeCanvas) {
                val checkPoint = saveLayer(null, null)
                with(mask) {
                    scale(
                        0.7f,
                        Offset(size.width / 1.96f, size.height / 1.8f),
                        block = { draw(size) })
                }
                drawLiquid(Color.Yellow)
                drawLiquid(Color.LightGray, offset = Offset(500f,100f))
                restoreToCount(checkPoint)
            }
        }


    }
}


fun DrawScope.drawLiquid(color: Color, offset: Offset = Offset(0f,0f)) {
    drawRect(
        color = color,
        topLeft = offset,
        size = Size(size.width * 0.5f, size.height * 0.5f),
        blendMode = BlendMode.SrcIn
    )
}
