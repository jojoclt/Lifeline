package com.example.lifeline.presentation.today.composables

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.lifeline.R
import com.example.lifeline.util.BitmapHelper

@Preview
@Composable
fun CupCanvas() {
    val context = LocalContext.current

    val mask = BitmapHelper.getVectorBitmap(context, R.drawable.coffeemask, 3)
    val glass = BitmapHelper.getVectorBitmap(context, R.drawable.coffeeempty,3)
    Box(
        modifier = Modifier
            .size(500.dp)
            .aspectRatio(1f)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            drawRect(
                color = Color.Transparent,
                size = size,
            )
            drawImage(
                image = mask,
                topLeft = Offset(
                    (size.width - mask.width) * 0.5f,
                    (size.height - mask.height) * 0.5F
                )
            )
            drawImage(
                image = glass,
                topLeft = Offset(
                    (size.width - glass.width) * 0.5f + (size.width/30),
                    (size.height - glass.height) * 0.5f
                )
            )
            //        drawCircle(color = Color.White, radius = 50F)

        }
    }
}

@Composable
fun DrawLine() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val height = size.height
        val width = size.width

        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = width, y = height),
            color = Color.Blue,
            strokeWidth = 16.0f
        )
    }
}