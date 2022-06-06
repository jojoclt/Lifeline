package com.example.lifeline.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette_1 = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    secondary = Red700,
    background = PrimaryColor,
    onPrimary = Color.Black,
    onSurface = Red700,
    onBackground = Color.Black
)

@Composable
fun LifelineTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette
//        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun myAppTextFieldColors(
    textColor: Color = Color.Black,
    disabledTextColor: Color = Color.Black,
    focusedLabelColor: Color = Color.Black,
    backgroundColor: Color = textBoxBg,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Red700
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    focusedLabelColor = focusedLabelColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor,

)

//textColor: Color,
//disabledTextColor: Color,
//backgroundColor: Color,
//cursorColor: Color,
//errorCursorColor: Color,
//focusedIndicatorColor: Color,
//unfocusedIndicatorColor: Color,
//disabledIndicatorColor: Color,
//errorIndicatorColor: Color,
//leadingIconColor: Color,
//disabledLeadingIconColor: Color,
//errorLeadingIconColor: Color,
//trailingIconColor: Color,
//disabledTrailingIconColor: Color,
//errorTrailingIconColor: Color,
//focusedLabelColor: Color,
//unfocusedLabelColor: Color,
//disabledLabelColor: Color,
//errorLabelColor: Color,
//placeholderColor: Color,
//disabledPlaceholderColor: Color