package com.orafaelsc.cstvfuze.domain.model

data class Match(
    val id: Int,
    val firstTeam: Team,
    val secondTeam: Team,
    val startTime: String?,
    val description: String,
    val leagueLogo: String,
    val status: MatchStatus,
)

fun Match.isLive(): Boolean = status == MatchStatus.RUNNING
