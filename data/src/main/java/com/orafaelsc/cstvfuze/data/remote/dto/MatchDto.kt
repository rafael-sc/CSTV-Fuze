package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchDto(
    val id: Int,
    val name: String,
    @SerialName("begin_at") val beginAt: String?,
    val status: String,
    val opponents: List<OpponentDto>,
    val league: LeagueDto,
    val serie: SerieDto,
)
