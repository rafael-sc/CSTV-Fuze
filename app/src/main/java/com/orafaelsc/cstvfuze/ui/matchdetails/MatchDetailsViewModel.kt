package com.orafaelsc.cstvfuze.ui.matchdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelsc.cstvfuze.R
import com.orafaelsc.cstvfuze.core.ResourceProvider
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.Team
import com.orafaelsc.cstvfuze.domain.usecase.MatchDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MatchDetailsViewModel(
    private val matchDetailsUseCase: MatchDetailsUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MatchDetailsState())
    val uiState: StateFlow<MatchDetailsState> = _uiState.asStateFlow()

    fun loadMatchData(match: Match) {
        if (_uiState.value.match != null) {
            return
        }
        _uiState.update { it.copy(match = match) }

        getTeamData(match.firstTeam)
        getTeamData(match.secondTeam)
    }

    fun getTeamData(team: Team) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            matchDetailsUseCase(team.id.toString())
                .onSuccess { teamDetails ->
                    Log.d("MatchDetailsViewModel", "Fetched team details: $teamDetails")
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            match =
                                state.match?.copy(
                                    firstTeam =
                                        if (state.match.firstTeam.id ==
                                            team.id
                                        ) {
                                            teamDetails
                                        } else {
                                            state.match.firstTeam
                                        },
                                    secondTeam =
                                        if (state.match.secondTeam.id ==
                                            team.id
                                        ) {
                                            teamDetails
                                        } else {
                                            state.match.secondTeam
                                        },
                                ),
                            error = null,
                        )
                    }
                }.onFailure { exception ->
                    Log.e("MatchDetailsViewModel", "Error fetching team details: ${exception.message}")
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = resourceProvider.getString(R.string.failed_to_load_team_details),
                        )
                    }
                }
        }
    }
}
