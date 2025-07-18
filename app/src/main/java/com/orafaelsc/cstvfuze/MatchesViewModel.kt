package com.orafaelsc.cstvfuze

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelsc.cstvfuze.domain.usecase.MatchesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MatchesViewModel(
    private val matchesUseCase: MatchesUseCase
) : ViewModel() {

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            matchesUseCase()
                .onStart {
                    // todo set loading state
                    Log.d("MatchesViewModel", "Fetching matches...")
                }
                .catch { exception ->
                    // todo handle error state
                    Log.d("MatchesViewModel", "Error fetching matches: ${exception.message}")

                }
                .collect { matches ->
                    // todo update UI state with matches
                    Log.d("MatchesViewModel", "Fetched matches: $matches")
                }
        }
    }

}
