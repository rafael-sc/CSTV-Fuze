package com.orafaelsc.cstvfuze.domain.model

data class Player(
    val id: Int,
    val name: String,
    val slug: String,
    val active: Boolean,
    val role: String?,
    val modifiedAt: String,
    val age: Int,
    val birthday: String,
    val firstName: String,
    val lastName: String,
    val nationality: String,
    val imageUrl: String?
)
