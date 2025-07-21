package com.orafaelsc.cstvfuze.data.remote

import com.orafaelsc.cstvfuze.data.remote.dto.MatchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchesApi {
    @GET("csgo/matches")
    suspend fun getMatches(
        @Query("sort") sortBy: String = "-status,begin_at", // Sort by running first, then by date
        @Query("filter[status]") status: String = "not_started,running,finished",
    ): Response<List<MatchDto>>
}
