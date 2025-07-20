package com.orafaelsc.cstvfuze.ui.components

import android.os.Build
import com.orafaelsc.cstvfuze.TestApplication
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.TIRAMISU],
    manifest = Config.NONE,
    application = TestApplication::class
)
open class BaseUITest
