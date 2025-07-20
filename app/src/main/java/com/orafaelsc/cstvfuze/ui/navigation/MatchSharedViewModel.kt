package com.orafaelsc.cstvfuze.ui.navigation

import androidx.lifecycle.ViewModel
import com.orafaelsc.cstvfuze.domain.model.Match
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MatchSharedViewModel : ViewModel() {
    private val _selectedMatch = MutableStateFlow<Match?>(null)
    val selectedMatch: StateFlow<Match?> = _selectedMatch.asStateFlow()

    fun selectMatch(match: Match) {
        _selectedMatch.value = match
    }

    fun clearSelectedMatch() {
        _selectedMatch.value = null
    }
}
