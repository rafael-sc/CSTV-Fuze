package com.orafaelsc.cstvfuze.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
    darkColorScheme(
        primary = PrimaryText,
        secondary = SecondaryText,
        background = PurpleDark,
        surface = PurpleDark,
        onPrimaryContainer = PurpleLight,
        onSecondaryContainer = PurpleGrey
    )


@Composable
fun CSTVFuzeccTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content,
    )
}
