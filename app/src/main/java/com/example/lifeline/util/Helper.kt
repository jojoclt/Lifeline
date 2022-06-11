package com.example.lifeline.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

object BitmapHelper {
    fun createScaledBitmap(context: Context, @DrawableRes img: Int, dimen: Int): ImageBitmap {
        val bitmap = ContextCompat.getDrawable(context, img)?.toBitmap()
        return Bitmap.createScaledBitmap(bitmap!!, dimen, dimen, false).asImageBitmap()
    }
    fun createBitmap(context: Context, @DrawableRes img: Int): ImageBitmap {
        return ContextCompat.getDrawable(context, img)?.toBitmap()!!.asImageBitmap()
    }
    fun getVectorBitmap(context: Context, @DrawableRes img: Int, scale: Int): ImageBitmap {
        val drawable = ContextCompat.getDrawable(context, img)!!
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth*scale,
            drawable.intrinsicHeight*scale, Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap.asImageBitmap()
    }
}

/**
 * helper function to draw a vector in a canvas
 */
object VectorHelper {
    @Composable
    fun createPainter(@DrawableRes img: Int): VectorPainter {
        val vector = ImageVector.vectorResource(img)
        return rememberVectorPainter(image = vector)
    }
}

/**
 * helper function to convert duration to String
 */
fun Int.toDuration(): String {
    val hour = this / 60
    val min = this % 60
    return String.format("%02d : %02d", hour, min)
}