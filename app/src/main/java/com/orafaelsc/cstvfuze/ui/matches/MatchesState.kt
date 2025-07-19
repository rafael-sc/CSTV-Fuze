package com.orafaelsc.cstvfuze.ui.matches

import com.orafaelsc.cstvfuze.domain.model.Match

data class MatchesState(
    val isLoading: Boolean = false,
    val matches: List<Match> = emptyList(),
    val error: String? = null,
)
