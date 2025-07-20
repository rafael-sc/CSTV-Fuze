package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueDto(
    val id: Int,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String?,
)
