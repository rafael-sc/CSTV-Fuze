package com.orafaelsc.cstvfuze.ui.components

import android.content.Context
import coil3.ColorImage
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.test.FakeImageLoaderEngine

object TestCoilUtils {
    /**
     * Installs a fake Coil ImageLoader into the singleton with given URL mappings.
     *
     * @param context The Android Context for building ImageLoader.
     * @param interceptMap Map of URL -> color Int. These URLs will return a solid ColorImage.
     * @param defaultColor A fallback color if URL is not found in interceptMap.
     */
    fun installFakeImageLoader(
        context: Context,
        interceptMap: Map<String, Int>,
        defaultColor: Int = 0xFF000000.toInt(),
    ) {
        val engineBuilder = FakeImageLoaderEngine.Builder()
        interceptMap.forEach { (url, color) ->
            engineBuilder.intercept(url, ColorImage(color))
        }
        engineBuilder.default(ColorImage(defaultColor))
        val engine = engineBuilder.build()

        val imageLoader =
            ImageLoader
                .Builder(context)
                .components { add(engine) }
                .build()

        // Swap the singleton for Compose
        SingletonImageLoader.setSafe { imageLoader }
    }
}
