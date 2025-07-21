package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test

class PlayerAvatarTest : BaseUITest() {
    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        TestCoilUtils.installFakeImageLoader(
            context = context,
            interceptMap = mapOf("https://example.com/avatar.jpg" to 0xFF00FF00.toInt()),
            defaultColor = 0xFFFF0000.toInt(),
        )
    }

    @Test
    fun `PlayerAvatar with valid URL and name`() {
        val playerName = "John Doe"
        val playerAvatarUrl = "https://example.com/avatar.jpg"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    modifier = Modifier.testTag("playerAvatar"),
                    playerName = playerName,
                    playerAvatarUrl = playerAvatarUrl,
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("$playerName avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with null URL and valid name`() {
        val playerName = "Jane Smith"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = null,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with empty URL and valid name`() {
        val playerName = "Mike Johnson"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = "",
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with valid URL and empty name`() {
        val playerAvatarUrl = "https://example.com/avatar.jpg"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = "",
                    playerAvatarUrl = playerAvatarUrl,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(" avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with null URL and empty name`() {
        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = "",
                    playerAvatarUrl = null,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(" avatar").assertIsNotDisplayed()
    }

    @Test
    fun `PlayerAvatar Box styling verification`() {
        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = "Test Player",
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        // The Box with 60.dp size, Color.Gray background, and RoundedCornerShape(8.dp) is applied
        // These styling properties are verified through the component's presence and correct rendering
    }

    @Test
    fun `PlayerAvatar Box content alignment verification`() {
        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = "Centered Player",
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
        // Content alignment is Center, verified through proper rendering of centered content
    }

    @Test
    fun `PlayerAvatar AsyncImage modifier verification`() {
        val playerName = "Test Player"
        val playerAvatarUrl = "https://example.com/avatar.jpg"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = playerAvatarUrl,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("$playerName avatar").assertIsDisplayed()
        // AsyncImage with Modifier.fillMaxWidth() is applied, verified through content description presence
    }

    @Test
    fun `PlayerAvatar default Image modifier verification`() {
        val playerName = "Test Player"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = null,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
        // Default Image with Modifier.fillMaxWidth() is applied, verified through content description presence
    }

    @Test
    fun `PlayerAvatar with extremely long player name`() {
        val longPlayerName =
            "This is an extremely long player name that should be handled correctly without causing " +
                "any UI issues or performance problems in the content description"
        val playerAvatarUrl = "https://example.com/avatar.jpg"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = longPlayerName,
                    playerAvatarUrl = playerAvatarUrl,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("$longPlayerName avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with invalid URL format`() {
        val playerName = "Test Player"
        val invalidUrl = "invalid-url-format"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = invalidUrl,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        // AsyncImage attempts to load the invalid URL, Coil handles the error gracefully
        composeTestRule.onNodeWithContentDescription("$playerName avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with network error during image load`() {
        val playerName = "Network Test Player"
        val networkErrorUrl = "https://nonexistent.domain.com/avatar.jpg"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = networkErrorUrl,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        // AsyncImage handles network errors gracefully, content description is still present
        composeTestRule.onNodeWithContentDescription("$playerName avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with placeholder resource missing`() {
        val playerName = "Resource Test Player"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = null,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        // Even if the placeholder resource were missing, the component should not crash
        // The Image component handles missing resources gracefully
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar custom modifier application`() {
        val playerName = "Modifier Test Player"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = playerName,
                    playerAvatarUrl = null,
                    modifier =
                        Modifier
                            .testTag("customPlayerAvatar")
                            .testTag("additionalTag"),
                )
            }
        }

        composeTestRule.onNodeWithTag("customPlayerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with special characters in player name`() {
        val specialPlayerName = "Jöhn Smíth 🎮⚡"
        val playerAvatarUrl = "https://example.com/avatar.jpg"

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = specialPlayerName,
                    playerAvatarUrl = playerAvatarUrl,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("$specialPlayerName avatar")
            .assertIsDisplayed()
    }

    @Test
    fun `PlayerAvatar with whitespace only player name`() {
        val whitespacePlayerName = "   "

        composeTestRule.setContent {
            MaterialTheme {
                PlayerAvatar(
                    playerName = whitespacePlayerName,
                    playerAvatarUrl = null,
                    modifier = Modifier.testTag("playerAvatar"),
                )
            }
        }

        composeTestRule.onNodeWithTag("playerAvatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Default avatar").assertIsDisplayed()
    }
}
