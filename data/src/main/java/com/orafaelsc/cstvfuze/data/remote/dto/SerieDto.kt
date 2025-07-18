package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SerieDto(
    val id: Int,
    @SerialName("full_name") val fullName: String
)