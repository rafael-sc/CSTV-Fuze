package com.orafaelsc.cstvfuze.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
object Colors {
    val PurpleDark = Color(0xFF161621)
    val PurpleLight = Color(0xFF272639)
    val PurpleGrey = Color(0xFF515060)
    val PrimaryText = Color(0xFFFFFFFF)
    val SecondaryText = Color(0xFF6c6b7e)
}
@Immutable
data class ExtendedColors(
    val textPrimary: Color,
    val textSecondary: Color,
) {
    companion object {
        val Default =
            ExtendedColors(
                textPrimary = Color(0xFFFFFFFF),
                textSecondary = Color(0xFF6c6b7e),
            )
    }
}
