package com.orafaelsc.cstvfuze.ui.matchdetails

import com.orafaelsc.cstvfuze.domain.model.Match

data class MatchDetailsState(
    val isLoading: Boolean = false,
    val match: Match? = null,
    val error: String? = null,
)
