package com.orafaelsc.cstvfuze.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
    darkColorScheme(
        primary = Colors.PrimaryText,
        secondary = Colors.SecondaryText,
        background = Colors.PurpleDark,
        surface = Colors.PurpleDark,
        onPrimaryContainer = Colors.PurpleLight,
        onSecondaryContainer = Colors.PurpleGrey,
    )

@Composable
fun CSTVFuzeccTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content,
    )
}
