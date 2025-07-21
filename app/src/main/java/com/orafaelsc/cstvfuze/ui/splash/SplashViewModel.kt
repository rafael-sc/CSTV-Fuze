package com.orafaelsc.cstvfuze.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            // fixed delay to simulate initialization
            delay(DELAY_TIME)

            _state.value =
                _state.value.copy(
                    isLoading = false,
                    isInitialized = true,
                )
        }
    }
    private companion object{
        const val DELAY_TIME = 2000L // 2 seconds

    }
}
