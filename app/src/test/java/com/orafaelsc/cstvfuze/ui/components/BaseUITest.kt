package com.orafaelsc.cstvfuze.ui.components

import android.os.Build
import androidx.compose.ui.test.junit4.createComposeRule
import com.orafaelsc.cstvfuze.TestApplication
import org.junit.Rule
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
open class BaseUITest(){
    @get:Rule
    val composeTestRule = createComposeRule()
}
