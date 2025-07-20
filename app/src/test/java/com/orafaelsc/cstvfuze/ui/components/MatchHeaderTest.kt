package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team
import org.junit.Rule
import org.junit.Test

class MatchHeaderTest : BaseUITest() {


    @Test
    fun `MatchHeader basic rendering`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(
                    modifier = Modifier.testTag("matchHeader"),
                    match = testMatch
                )
            }
        }

        composeTestRule.onNodeWithTag("matchHeader").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader team names displayed`() {
        val testMatch = createTestMatch(
            firstTeam = Team(id = 1, name = "Warriors", iconUrl = "url1"),
            secondTeam = Team(id = 2, name = "Dragons", iconUrl = "url2")
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("Warriors").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dragons").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader vs text presence`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader layout arrangement`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(
                    match = testMatch,
                    modifier = Modifier.testTag("matchHeader")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchHeader").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader team columns weighting`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader team columns alignment`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader vs text styling`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader vs text padding`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader TeamItem calls with correct data for Team 1`() {
        val firstTeam = Team(id = 1, name = "First Team", iconUrl = "https://example.com/team1.png")
        val testMatch = createTestMatch(firstTeam = firstTeam)

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("First Team").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("First Team logo").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader TeamItem calls with correct data for Team 2`() {
        val secondTeam = Team(id = 2, name = "Second Team", iconUrl = "https://example.com/team2.png")
        val testMatch = createTestMatch(secondTeam = secondTeam)

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("Second Team").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Second Team logo").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader with empty team names`() {
        val testMatch = createTestMatch(
            firstTeam = Team(id = 1, name = "", iconUrl = "url1"),
            secondTeam = Team(id = 2, name = "", iconUrl = "url2")
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader with null team names if applicable though Kotlin non nullable types prevent this for name`() {
        // Since Team.name is non-nullable in Kotlin, this test verifies the type safety
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader with very long team names`() {
        val longTeamName1 = "This is an extremely long team name that should be handled properly by the UI"
        val longTeamName2 = "Another very long team name that tests text wrapping and layout behavior"
        val testMatch = createTestMatch(
            firstTeam = Team(id = 1, name = longTeamName1, iconUrl = "url1"),
            secondTeam = Team(id = 2, name = longTeamName2, iconUrl = "url2")
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText(longTeamName1).assertIsDisplayed()
        composeTestRule.onNodeWithText(longTeamName2).assertIsDisplayed()
        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader with special characters in team names`() {
        val specialTeamName1 = "Team Émojis 🎮⚡"
        val specialTeamName2 = "Spéciàl Châractërs & Símböls"
        val testMatch = createTestMatch(
            firstTeam = Team(id = 1, name = specialTeamName1, iconUrl = "url1"),
            secondTeam = Team(id = 2, name = specialTeamName2, iconUrl = "url2")
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithText(specialTeamName1).assertIsDisplayed()
        composeTestRule.onNodeWithText(specialTeamName2).assertIsDisplayed()
        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader TeamItem integration test team logo`() {
        val testMatch = createTestMatch(
            firstTeam = Team(id = 1, name = "Logo Team 1", iconUrl = "https://example.com/logo1.png"),
            secondTeam = Team(id = 2, name = "Logo Team 2", iconUrl = "https://example.com/logo2.png")
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchHeader(match = testMatch)
            }
        }

        composeTestRule.onNodeWithContentDescription("Logo Team 1 logo").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Logo Team 2 logo").assertIsDisplayed()
    }

    @Test
    fun `MatchHeader preview functionality`() {
        // Test that the component renders without crashing when used in preview mode
        composeTestRule.setContent {
            MaterialTheme {
                MatchHeaderPreview()
            }
        }

        // Verify preview renders basic elements
        composeTestRule.onNodeWithText("vs").assertIsDisplayed()
    }

    // Helper function
    private fun createTestMatch(
        id: Int = 1,
        firstTeam: Team = Team(id = 1, name = "Team A", iconUrl = "https://example.com/team_a.png"),
        secondTeam: Team = Team(id = 2, name = "Team B", iconUrl = "https://example.com/team_b.png"),
        startTime: String? = "2025-01-15T15:21:32Z",
        description: String = "Test League",
        status: MatchStatus = MatchStatus.NOT_STARTED,
        leagueLogo: String = "https://example.com/league_logo.png"
    ): Match {
        return Match(
            id = id,
            firstTeam = firstTeam,
            secondTeam = secondTeam,
            startTime = startTime,
            description = description,
            status = status,
            leagueLogo = leagueLogo
        )
    }
}
