package com.orafaelsc.cstvfuze.domain.model

import java.time.LocalDateTime

data class Match(
    val id: Int,
    val firstTeam: Team,
    val secondTeam: Team,
    val startTime: LocalDateTime?,
    val description: String,
    val starTimeText: String,
    val leagueLogo: String,
    val status: MatchStatus,
)

fun Match.isLive(): Boolean {
    return status == MatchStatus.RUNNING
}