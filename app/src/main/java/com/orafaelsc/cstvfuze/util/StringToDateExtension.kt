package com.orafaelsc.cstvfuze.util

import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDateTime? =
    try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val data = LocalDateTime.parse(this, formatter)
        data
            .atZone(ZoneOffset.UTC)
            .withZoneSameInstant(ZoneId.systemDefault())
            .toLocalDateTime()
    } catch (e: Exception) {
        Log.i("String.toLocalDate()", e.message.toString())
        null
    }
