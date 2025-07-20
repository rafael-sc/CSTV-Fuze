package com.orafaelsc.cstvfuze.data.remote

import com.orafaelsc.cstvfuze.data.remote.dto.LeagueDto
import com.orafaelsc.cstvfuze.data.remote.dto.MatchDto
import com.orafaelsc.cstvfuze.data.remote.dto.TeamDto
import com.orafaelsc.cstvfuze.domain.model.League
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team

fun MatchDto.toDomain(): Match {
    val firstTeam = opponents.firstOrNull()?.opponent?.toDomain() ?: Team(0, "Unknown", null)
    val secondTeam = opponents.firstOrNull()?.opponent?.toDomain() ?: Team(0, "Unknown", null)

    return Match(
        id = id,
        firstTeam = firstTeam,
        secondTeam = secondTeam,
        startTime = beginAt,
        description = league.name + " - " + serie.fullName,
        leagueLogo = league.imageUrl.orEmpty(),
        status = getStatus(status)
    )
}

private fun getStatus(status: String?): MatchStatus {
    return when (status) {
        "not_started" -> MatchStatus.NOT_STARTED
        "running" -> MatchStatus.RUNNING
        "finished" -> MatchStatus.FINISHED
        else -> MatchStatus.UNKNOWN
    }
}


fun LeagueDto.toDomain(): League = League(id, name, imageUrl)

fun TeamDto.toDomain(): Team = Team(id, name, imageUrl)
