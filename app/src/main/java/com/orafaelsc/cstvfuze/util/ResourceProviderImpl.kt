package com.orafaelsc.cstvfuze.util

import android.content.Context
import com.orafaelsc.cstvfuze.core.ResourceProvider

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}
