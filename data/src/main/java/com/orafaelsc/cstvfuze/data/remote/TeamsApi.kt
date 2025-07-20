package com.orafaelsc.cstvfuze.data.remote

import com.orafaelsc.cstvfuze.data.remote.dto.TeamDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsApi {
    @GET("csgo/teams/{team_id}")
    suspend fun getTeamDetails(
        @Query("team_id") teamId: String
    ): Response<TeamDto>
}