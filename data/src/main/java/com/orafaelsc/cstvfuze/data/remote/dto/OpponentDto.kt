package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class OpponentDto(
    val opponent: TeamDto,
)
