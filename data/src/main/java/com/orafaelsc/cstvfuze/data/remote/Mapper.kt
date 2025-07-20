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
        startTime = beginAt?.toLocalDate(),
        description = league.name + " - " + serie.fullName,
        starTimeText = getStartTime(beginAt),
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

private fun getStartTime(beginAt: String?): String {
    return beginAt?.toLocalDate()?.run {
        val now = LocalDateTime.now()
        when {
            now.isAfter(this) -> "AGORA"
            now.isBefore(this) && this.isBefore(
                LocalDateTime.now().plusDays(1)
            ) -> "Hoje, " + DateTimeFormatter.ofPattern("HH:mm").format(this)
            else -> DateTimeFormatter.ofPattern("EEE, HH:mm").format(this).replace(".", "")
                .replaceFirstChar { it.uppercase() }
        }
    } ?: ""
}


private fun String.toLocalDate(): LocalDateTime? {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val data = LocalDateTime.parse(this, formatter)
    return data.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
}

fun LeagueDto.toDomain(): League = League(id, name, imageUrl)

fun TeamDto.toDomain(): Team = Team(id, name, imageUrl)
