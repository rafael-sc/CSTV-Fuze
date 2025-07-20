package com.orafaelsc.cstvfuze.core

interface ResourceProvider {
    fun getString(resId: Int): String

    fun getString(
        resId: Int,
        vararg formatArgs: Any,
    ): String
}
