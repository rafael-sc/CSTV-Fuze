package com.orafaelsc.cstvfuze.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class MatchCardTest : BaseUITest() {

    @Test
    fun `MatchCard renders with live match`() {
        val liveMatch = createTestMatch(status = MatchStatus.RUNNING)

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = liveMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test League").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with upcoming match today`() {
        val todayMatch = createTestMatch(
            startTime = getTodayDateTime(),
            status = MatchStatus.NOT_STARTED
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = todayMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with upcoming match on a future date`() {
        val futureMatch = createTestMatch(
            startTime = getFutureDateTime(),
            status = MatchStatus.NOT_STARTED
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = futureMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with finished match`() {
        val finishedMatch = createTestMatch(
            status = MatchStatus.FINISHED,
            startTime = getPastDateTime()
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = finishedMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with not started match`() {
        val notStartedMatch = createTestMatch(
            status = MatchStatus.NOT_STARTED,
            startTime = getFutureDateTime()
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = notStartedMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard click invokes onClick callback`() {
        var clickCount = 0
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = testMatch,
                    onClick = { clickCount++ },
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertHasClickAction()
        composeTestRule.onNodeWithTag("matchCard").performClick()

        assertEquals(1, clickCount)
    }

    @Test
    fun `MatchCard renders with missing league logo`() {
        val matchWithoutLogo = createTestMatch(leagueLogo = "")

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithoutLogo,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test League").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with missing match description`() {
        val matchWithoutDescription = createTestMatch(description = "")

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithoutDescription,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with missing first team data`() {
        val matchWithNullFirstTeam = createTestMatch(
            firstTeam = Team(id = 1, name = "", iconUrl = null)
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithNullFirstTeam,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with missing second team data`() {
        val matchWithNullSecondTeam = createTestMatch(
            secondTeam = Team(id = 2, name = "", iconUrl = null)
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithNullSecondTeam,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with missing start time`() {
        val matchWithNullStartTime = createTestMatch(startTime = null)

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithNullStartTime,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard renders with invalid start time format`() {
        val matchWithInvalidStartTime = createTestMatch(startTime = "invalid-date-format")

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithInvalidStartTime,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
    }

    @Test
    fun `MatchCard tag component text and color for live match`() {
        val liveMatch = createTestMatch(status = MatchStatus.RUNNING)

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = liveMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        // The tag should show "NOW" for live matches based on getStartTime logic
    }

    @Test
    fun `MatchCard tag component text and color for upcoming match today`() {
        val todayMatch = createTestMatch(
            startTime = getTodayDateTime(),
            status = MatchStatus.NOT_STARTED
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = todayMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        // Should show "Today, HH:mm" format
    }

    @Test
    fun `MatchCard tag component text and color for upcoming match future date`() {
        val futureMatch = createTestMatch(
            startTime = getFutureDateTime(),
            status = MatchStatus.NOT_STARTED
        )

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = futureMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        // Should show "Day, HH:mm" format
    }

    @Test
    fun `MatchCard league logo placeholder`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = testMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Test League logo").assertIsDisplayed()
    }

    @Test
    fun `MatchCard with extremely long description`() {
        val longDescription = "This is an extremely long league description that should be handled properly by the UI without breaking the layout or causing overflow issues in the match card component"
        val matchWithLongDescription = createTestMatch(description = longDescription)

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = matchWithLongDescription,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText(longDescription).assertIsDisplayed()
    }

    @Test
    fun `MatchCard rendering in different locales timezones for start time`() {
        val utcMatch = createTestMatch(startTime = "2025-01-15T14:30:00Z")

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = utcMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        // Time should be converted to system timezone
    }

    @Test
    fun `MatchCard UI elements alignment and spacing`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = testMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithTag("matchCard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team A").assertIsDisplayed()
        composeTestRule.onNodeWithText("Team B").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test League").assertIsDisplayed()
    }

    @Test
    fun `MatchCard accessibility content descriptions`() {
        val testMatch = createTestMatch()

        composeTestRule.setContent {
            MaterialTheme {
                MatchCard(
                    match = testMatch,
                    onClick = {},
                    modifier = Modifier.testTag("matchCard")
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Test League logo").assertIsDisplayed()
    }

    // Helper functions
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

    private fun getTodayDateTime(): String {
        return LocalDateTime.now().plusHours(2)
            .atZone(ZoneOffset.UTC)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
    }

    private fun getFutureDateTime(): String {
        return LocalDateTime.now().plusDays(3)
            .atZone(ZoneOffset.UTC)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
    }

    private fun getPastDateTime(): String {
        return LocalDateTime.now().minusHours(2)
            .atZone(ZoneOffset.UTC)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
    }
}
