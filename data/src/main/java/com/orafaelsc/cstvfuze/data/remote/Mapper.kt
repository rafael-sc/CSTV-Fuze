package com.orafaelsc.cstvfuze.data.remote

import com.orafaelsc.cstvfuze.data.remote.dto.LeagueDto
import com.orafaelsc.cstvfuze.data.remote.dto.MatchDto
import com.orafaelsc.cstvfuze.data.remote.dto.TeamDto
import com.orafaelsc.cstvfuze.domain.model.League
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team
import java.time.LocalDateTime

fun MatchDto.toDomain(): Match {
    val firstTeam = opponents.firstOrNull()?.opponent?.toDomain() ?: Team(0, "Unknown", null)
    val secondTeam = opponents.getOrNull(1)?.opponent?.toDomain() ?: Team(0, "Unknown", null)

    return Match(
        id = id,
        firstTeam = firstTeam,
        secondTeam = secondTeam,
        startTime = beginAt?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now(),
        description = status,
        starTimeText = beginAt ?: "Unknown",
        leagueLogo = league.imageUrl.orEmpty(),
        status = when (status) {
            "finished" -> MatchStatus.FINISHED
            "live" -> MatchStatus.RUNNING
            "not_started" -> MatchStatus.NOT_STARTED
            else -> MatchStatus.NOT_STARTED
        }
    )
}

fun LeagueDto.toDomain(): League = League(id, name, imageUrl)

fun TeamDto.toDomain(): Team = Team(id, name, imageUrl)
