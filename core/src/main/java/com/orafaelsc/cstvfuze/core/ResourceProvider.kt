package com.orafaelsc.cstvfuze.core

interface ResourceProvider {
    fun getString(
        id: Int,
        vararg params: Any,
    ): String
}
