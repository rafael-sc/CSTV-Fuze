package com.orafaelsc.cstvfuze.domain.repository

import com.orafaelsc.cstvfuze.domain.model.Team

interface TeamDetailsRepository {
    suspend fun getTeamDetails(teamId: String): Result<Team>
}
