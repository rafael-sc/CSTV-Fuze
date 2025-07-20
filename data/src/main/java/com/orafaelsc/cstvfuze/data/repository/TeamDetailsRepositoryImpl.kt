package com.orafaelsc.cstvfuze.data.repository
import com.orafaelsc.cstvfuze.data.remote.TeamsApi
import com.orafaelsc.cstvfuze.data.remote.toDomain
import com.orafaelsc.cstvfuze.domain.model.Team
import com.orafaelsc.cstvfuze.domain.repository.TeamDetailsRepository

class TeamDetailsRepositoryImpl(
    private val teamApi: TeamsApi,
) : TeamDetailsRepository {
    override suspend fun getTeamDetails(teamId: String): Result<Team> =
        try {
            val response = teamApi.getTeamDetails(teamId)
            if (response.isSuccessful) {
                val teamDto = response.body()
                if (teamDto != null) {
                    Result.success(teamDto.toDomain())
                } else {
                    Result.failure(Exception("Team not found"))
                }
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
}
