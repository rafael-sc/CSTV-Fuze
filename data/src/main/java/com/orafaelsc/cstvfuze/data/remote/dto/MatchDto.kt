package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchDto(
    val id: Int,
    @SerialName("begin_at")
    val beginAt: String?,
    val status: String,
    val league: LeagueDto,
    val opponents: List<OpponentDto>,
)
