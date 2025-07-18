package com.orafaelsc.cstvfuze.data.remote

import com.orafaelsc.cstvfuze.data.remote.dto.LeagueDto
import com.orafaelsc.cstvfuze.data.remote.dto.MatchDto
import com.orafaelsc.cstvfuze.data.remote.dto.TeamDto
import com.orafaelsc.cstvfuze.domain.model.League
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun MatchDto.toDomain(): Match {
    val firstTeam = opponents.firstOrNull()?.opponent?.toDomain() ?: Team(0, "Unknown", null)
    val secondTeam = opponents.getOrNull(1)?.opponent?.toDomain() ?: Team(0, "Unknown", null)

    return Match(
        id = id,
        firstTeam = firstTeam,
        secondTeam = secondTeam,
        startTime =  beginAt?.toLocalDate(),
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

private fun String.toLocalDate(): LocalDateTime? {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val data = LocalDateTime.parse(this, formatter)
    return data.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}

fun LeagueDto.toDomain(): League = League(id, name, imageUrl)

fun TeamDto.toDomain(): Team = Team(id, name, imageUrl)
