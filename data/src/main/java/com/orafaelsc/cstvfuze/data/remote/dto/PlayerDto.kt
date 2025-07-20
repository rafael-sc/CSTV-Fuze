package com.orafaelsc.cstvfuze.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlayerDto(
    val id: Int,
    val active: Boolean,
    val name: String?,
    val role: String?,
    val slug: String?,
    @SerialName("modified_at") val modifiedAt: String?,
    @SerialName("first_name") val firstName: String?,
    @SerialName("last_name") val lastName: String?,
    val nationality: String,
    @SerialName("image_url") val imageUrl: String?
)
