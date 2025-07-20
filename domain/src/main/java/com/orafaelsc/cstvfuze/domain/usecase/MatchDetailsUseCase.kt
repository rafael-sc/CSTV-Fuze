package com.orafaelsc.cstvfuze.domain.usecase

import com.orafaelsc.cstvfuze.domain.model.Team
import com.orafaelsc.cstvfuze.domain.repository.TeamDetailsRepository

class MatchDetailsUseCase(
    private val teamDetailsRepository: TeamDetailsRepository,
) {
    suspend operator fun invoke(teamId: String): Result<Team> =
        teamDetailsRepository.getTeamDetails(teamId)
}
