package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.orafaelsc.cstvfuze.domain.model.Player
import org.junit.Before
import org.junit.Test

class PlayersSectionTest : BaseUITest() {

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        TestCoilUtils.installFakeImageLoader(
            context = context,
            interceptMap = mapOf("https://example.com/avatar.jpg" to 0xFF00FF00.toInt()),
            defaultColor = 0xFFFF0000.toInt()
        )
    }


    @Test
    fun `PlayersSection renders correctly with equal number of players`() {
        val team1Players = listOf(
            createTestPlayer(id = 1, name = "Player1", firstName = "John", lastName = "Doe"),
            createTestPlayer(id = 2, name = "Player2", firstName = "Jane", lastName = "Smith")
        )
        val team2Players = listOf(
            createTestPlayer(id = 3, name = "Player3", firstName = "Bob", lastName = "Wilson"),
            createTestPlayer(id = 4, name = "Player4", firstName = "Alice", lastName = "Brown")
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player4").assertIsDisplayed()
        composeTestRule.onNodeWithText("John Doe").assertIsDisplayed()
        composeTestRule.onNodeWithText("Jane Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bob Wilson").assertIsDisplayed()
        composeTestRule.onNodeWithText("Alice Brown").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection renders correctly when team 1 has more players`() {
        val team1Players = listOf(
            createTestPlayer(id = 1, name = "Player1"),
            createTestPlayer(id = 2, name = "Player2"),
            createTestPlayer(id = 3, name = "Player3")
        )
        val team2Players = listOf(
            createTestPlayer(id = 4, name = "Player4")
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player4").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection renders correctly when team 2 has more players`() {
        val team1Players = listOf(
            createTestPlayer(id = 1, name = "Player1")
        )
        val team2Players = listOf(
            createTestPlayer(id = 2, name = "Player2"),
            createTestPlayer(id = 3, name = "Player3"),
            createTestPlayer(id = 4, name = "Player4")
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player3").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player4").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection renders correctly with empty player lists`() {
        val team1Players = emptyList<Player>()
        val team2Players = emptyList<Player>()

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        // No player cards should be rendered
        composeTestRule.onAllNodesWithContentDescription("avatar", substring = true).assertCountEquals(0)
    }

    @Test
    fun `PlayersSection renders correctly when team 1 is empty`() {
        val team1Players = emptyList<Player>()
        val team2Players = listOf(
            createTestPlayer(id = 1, name = "Player1"),
            createTestPlayer(id = 2, name = "Player2")
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player2").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection renders correctly when team 2 is empty`() {
        val team1Players = listOf(
            createTestPlayer(id = 1, name = "Player1"),
            createTestPlayer(id = 2, name = "Player2")
        )
        val team2Players = emptyList<Player>()

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player2").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection PlayerCard content verification for team 1`() {
        val team1Player = createTestPlayer(
            id = 1,
            name = "Team1Player",
            firstName = "John",
            lastName = "Doe",
            slug = "team1-slug",
            imageUrl = "https://example.com/team1player.png"
        )
        val team1Players = listOf(team1Player)
        val team2Players = emptyList<Player>()

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithText("Team1Player").assertIsDisplayed()
        composeTestRule.onNodeWithText("John Doe").assertIsDisplayed()
        // Team 1 players should have showAvatarAtStart = false (avatar at end)
        composeTestRule.onNodeWithContentDescription("team1-slug avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection PlayerCard content verification for team 2`() {
        val team2Player = createTestPlayer(
            id = 2,
            name = "Team2Player",
            firstName = "Jane",
            lastName = "Smith",
            slug = "team2-slug",
            imageUrl = "https://example.com/team2player.png"
        )
        val team1Players = emptyList<Player>()
        val team2Players = listOf(team2Player)

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithText("Team2Player").assertIsDisplayed()
        composeTestRule.onNodeWithText("Jane Smith").assertIsDisplayed()
        // Team 2 players should have showAvatarAtStart = true (avatar at start)
        composeTestRule.onNodeWithContentDescription("Team2Player avatar").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection layout verification with multiple players`() {
        val team1Players = listOf(
            createTestPlayer(id = 1, name = "T1P1"),
            createTestPlayer(id = 2, name = "T1P2"),
            createTestPlayer(id = 3, name = "T1P3")
        )
        val team2Players = listOf(
            createTestPlayer(id = 4, name = "T2P1"),
            createTestPlayer(id = 5, name = "T2P2")
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        // Verify all players are displayed
        composeTestRule.onNodeWithText("T1P1").assertIsDisplayed()
        composeTestRule.onNodeWithText("T1P2").assertIsDisplayed()
        composeTestRule.onNodeWithText("T1P3").assertIsDisplayed()
        composeTestRule.onNodeWithText("T2P1").assertIsDisplayed()
        composeTestRule.onNodeWithText("T2P2").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection layout with single player in each team`() {
        val team1Players = listOf(createTestPlayer(id = 1, name = "SinglePlayer1"))
        val team2Players = listOf(createTestPlayer(id = 2, name = "SinglePlayer2"))

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("SinglePlayer1").assertIsDisplayed()
        composeTestRule.onNodeWithText("SinglePlayer2").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection handles players with null or empty fields gracefully`() {
        val team1Players = listOf(
            createTestPlayer(id = 1, name = "", firstName = "", lastName = "", imageUrl = null),
            createTestPlayer(id = 2, name = "ValidPlayer", firstName = "Valid", lastName = "Player")
        )
        val team2Players = listOf(
            createTestPlayer(id = 3, name = "AnotherPlayer", firstName = "", lastName = "", imageUrl = "")
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("ValidPlayer").assertIsDisplayed()
        composeTestRule.onNodeWithText("Valid Player").assertIsDisplayed()
        composeTestRule.onNodeWithText("AnotherPlayer").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection modifier application`() {
        val team1Players = listOf(createTestPlayer(id = 1, name = "Player1"))
        val team2Players = listOf(createTestPlayer(id = 2, name = "Player2"))

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier
                        .testTag("customPlayersSection")
                        .testTag("additionalTag"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("customPlayersSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Player2").assertIsDisplayed()
    }

    @Test
    fun `PlayersSection accessibility checks`() {
        val team1Players = listOf(
            createTestPlayer(
                id = 1,
                name = "AccessiblePlayer1",
                slug = "accessible1",
                imageUrl = "https://example.com/player1.png"
            )
        )
        val team2Players = listOf(
            createTestPlayer(
                id = 2,
                name = "AccessiblePlayer2",
                slug = "accessible2",
                imageUrl = "https://example.com/player2.png"
            )
        )

        composeTestRule.setContent {
            MaterialTheme {
                PlayersSection(
                    modifier = Modifier.testTag("playersSection"),
                    team1Players = team1Players,
                    team2Players = team2Players
                )
            }
        }

        composeTestRule.onNodeWithTag("playersSection").assertIsDisplayed()
        // Verify accessibility content descriptions are present
        composeTestRule.onNodeWithContentDescription("accessible1 avatar").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("AccessiblePlayer2 avatar").assertIsDisplayed()
    }

    // Helper function
    private fun createTestPlayer(
        id: Int,
        name: String = "TestPlayer",
        slug: String = "test-slug",
        active: Boolean = true,
        role: String? = "Role",
        modifiedAt: String = "2023-01-01T00:00:00Z",
        firstName: String = "Test",
        lastName: String = "Player",
        nationality: String = "US",
        imageUrl: String? = "https://example.com/player.png"
    ): Player {
        return Player(
            id = id,
            name = name,
            slug = slug,
            active = active,
            role = role,
            modifiedAt = modifiedAt,
            firstName = firstName,
            lastName = lastName,
            nationality = nationality,
            imageUrl = imageUrl
        )
    }
}
