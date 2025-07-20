package com.orafaelsc.cstvfuze.ui.matches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.core.ResourceProvider
import com.orafaelsc.cstvfuze.domain.usecase.MatchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MatchesViewModel(
    private val matchesUseCase: MatchesUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MatchesState())
    val uiState: StateFlow<MatchesState> = _uiState.asStateFlow()

    init {
        fetchMatches()
    }

    fun fetchMatches() {
        viewModelScope.launch {
            matchesUseCase()
                .onStart {
                    Log.d("MatchesViewModel", "Fetching matches...")
                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                }.catch { exception ->
                    Log.d("MatchesViewModel", "Error fetching matches: ${exception.message}")
                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            error = resourceProvider.getString(R.string.failed_to_load_matches),
                        )
                }.collect { matches ->
                    Log.d("MatchesViewModel", "Fetched matches: $matches")
                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            matches = matches,
                        )
                }
        }
    }
}
