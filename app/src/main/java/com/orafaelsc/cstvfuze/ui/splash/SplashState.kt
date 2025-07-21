package com.orafaelsc.cstvfuze.ui.splash

import androidx.compose.runtime.Immutable

@Immutable
data class SplashState(
    val isLoading: Boolean = true,
    val isInitialized: Boolean = false,
)
