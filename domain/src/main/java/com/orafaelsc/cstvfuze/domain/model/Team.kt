package com.orafaelsc.cstvfuze.domain.model

data class Team(
    val id: Int,
    val name: String,
    val iconUrl: String?,
    val players: List<Player>? = null,
)
