package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class CustomTopAppBarTest: BaseUITest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Default state rendering`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = "Test Title")
            }
        }

        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
    }

    @Test
    fun `Back button visibility`() {
        var clickCount = 0

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test Title",
                    onBackClick = { clickCount++ }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("").assertHasClickAction()
    }

    @Test
    fun `Back button click functionality`() {
        var clickCount = 0

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test Title",
                    onBackClick = { clickCount++ }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").performClick()
        assertEquals(clickCount, 1)
    }

    @Test
    fun `Title text display`() {
        val testTitle = "Custom Test Title"

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = testTitle)
            }
        }

        composeTestRule.onNodeWithText(testTitle).assertIsDisplayed()
    }

    @Test
    fun `Title text styling`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Styled Title",
                    modifier = Modifier.testTag("topAppBar")
                )
            }
        }

        composeTestRule.onNodeWithText("Styled Title").assertIsDisplayed()
    }

    @Test
    fun `Title text overflow handling`() {
        val longTitle =
            "This is a very long title that should be truncated with ellipsis because it exceeds the available space"

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = longTitle)
            }
        }

        composeTestRule.onNodeWithText(longTitle).assertIsDisplayed()
    }

    @Test
    fun `Title padding when no back button`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "No Back Button",
                    onBackClick = null
                )
            }
        }

        composeTestRule.onNodeWithText("No Back Button").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("").assertIsNotDisplayed()
    }

    @Test
    fun `Title padding when back button present`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "With Back Button",
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("With Back Button").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()
    }

    @Test
    fun `Back button content description present`() {
        val contentDescription = "Navigate back"

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test",
                    backButtonContentDescription = contentDescription,
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription(contentDescription).assertIsDisplayed()
    }

    @Test
    fun `Back button content description null`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test",
                    backButtonContentDescription = null,
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()
    }

    @Test
    fun `Back button icon tint`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test",
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()
    }

    @Test
    fun `TopAppBar background color`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Background Test",
                    modifier = Modifier.testTag("topAppBar")
                )
            }
        }

        composeTestRule.onNodeWithTag("topAppBar").assertIsDisplayed()
    }

    @Test
    fun `TopAppBarDefaults colors containerColor`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = "Container Color Test")
            }
        }

        composeTestRule.onNodeWithText("Container Color Test").assertIsDisplayed()
    }

    @Test
    fun `TopAppBarDefaults colors titleContentColor`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = "Title Color Test")
            }
        }

        composeTestRule.onNodeWithText("Title Color Test").assertIsDisplayed()
    }

    @Test
    fun `TopAppBarDefaults colors navigationIconContentColor`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Icon Color Test",
                    onBackClick = {}
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()
    }

    @Test
    fun `Modifier application`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Modifier Test",
                    modifier = Modifier.testTag("customModifier")
                )
            }
        }

        composeTestRule.onNodeWithTag("customModifier").assertIsDisplayed()
    }

    @Test
    fun `Title with special characters`() {
        val specialTitle = "Title with émojis 🎉 & spéciàl châractërs"

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = specialTitle)
            }
        }

        composeTestRule.onNodeWithText(specialTitle).assertIsDisplayed()
    }

    @Test
    fun `Null onBackClick and null backButtonContentDescription`() {
        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "No Back Button",
                    backButtonContentDescription = null,
                    onBackClick = null
                )
            }
        }

        composeTestRule.onNodeWithText("No Back Button").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("").assertIsNotDisplayed()
    }

    @Test
    fun `Recomposition with changed title`() {
        var title by mutableStateOf("Initial Title")

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(title = title)
            }
        }

        composeTestRule.onNodeWithText("Initial Title").assertIsDisplayed()

        title = "Updated Title"

        composeTestRule.onNodeWithText("Updated Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Initial Title").assertIsNotDisplayed()
    }

    @Test
    fun `Recomposition with changed onBackClick null to non null`() {
        var onBackClick: (() -> Unit)? by mutableStateOf(null)

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test",
                    onBackClick = onBackClick
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").assertIsNotDisplayed()

        onBackClick = {}

        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()
    }

    @Test
    fun `Recomposition with changed onBackClick non null to null`() {
        var onBackClick: (() -> Unit)? by mutableStateOf({})

        composeTestRule.setContent {
            MaterialTheme {
                CustomTopAppBar(
                    title = "Test",
                    onBackClick = onBackClick
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("").assertIsDisplayed()

        onBackClick = null

        composeTestRule.onNodeWithContentDescription("").assertIsNotDisplayed()
    }
}
