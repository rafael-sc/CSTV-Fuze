package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlayerDto(
    val id: Int,
    @SerialName("name") val name: String,
    val nickname: String,
    @SerialName("image_url") val imageUrl: String?
)