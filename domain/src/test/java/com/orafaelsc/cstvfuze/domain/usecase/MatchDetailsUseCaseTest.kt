package com.orafaelsc.cstvfuze.domain.usecase

import com.orafaelsc.cstvfuze.domain.model.Player
import com.orafaelsc.cstvfuze.domain.model.Team
import com.orafaelsc.cstvfuze.domain.repository.TeamDetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class MatchDetailsUseCaseTest {
    private lateinit var teamsDetailsRepository: TeamDetailsRepository
    private lateinit var matchDetailsUseCase: MatchDetailsUseCase

    @Before
    fun setUp() {
        teamsDetailsRepository = mockk()
        matchDetailsUseCase = MatchDetailsUseCase(teamsDetailsRepository)
    }

    @Test
    fun `invoke returns success when repository returns team with players`() =
        runTest {
            // Given
            val teamId = "123"
            val expectedTeam =
                createTestTeam(
                    id = 123,
                    name = "Test Team",
                    players =
                        listOf(
                            createTestPlayer(id = 1, name = "Player1"),
                            createTestPlayer(id = 2, name = "Player2"),
                        ),
                )
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(expectedTeam, result.getOrThrow())
        }

    @Test
    fun `invoke returns success when repository returns team with empty players list`() =
        runTest {
            // Given
            val teamId = "456"
            val expectedTeam =
                createTestTeam(
                    id = 456,
                    name = "Empty Team",
                    players = emptyList(),
                )
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(expectedTeam, result.getOrThrow())
            assertTrue(result.getOrThrow().players?.isEmpty() == true)
        }

    @Test
    fun `invoke returns failure when repository returns failure`() =
        runTest {
            // Given
            val teamId = "789"
            val expectedError = RuntimeException("Network error")
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.failure(expectedError)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isFailure)
            assertEquals(expectedError, result.exceptionOrNull())
        }

    @Test
    fun `invoke returns failure when repository throws exception`() =
        runTest {
            // Given
            val teamId = "999"
            val expectedException = IllegalStateException("Unexpected error")
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } throws expectedException

            // When & Then
            assertFailsWith<IllegalStateException> {
                matchDetailsUseCase(teamId)
            }
        }

    @Test
    fun `invoke handles empty team id correctly`() =
        runTest {
            // Given
            val teamId = ""
            val expectedTeam = createTestTeam(id = 0, name = "Default Team")
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(expectedTeam, result.getOrThrow())
        }

    @Test
    fun `invoke handles numeric team id as string correctly`() =
        runTest {
            // Given
            val teamId = "12345"
            val expectedTeam =
                createTestTeam(
                    id = 12345,
                    name = "Numeric ID Team",
                    players = listOf(createTestPlayer(id = 100, name = "NumericPlayer")),
                )
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(expectedTeam, result.getOrThrow())
            assertEquals(1, result.getOrThrow().players?.size)
        }

    @Test
    fun `invoke preserves team data integrity`() =
        runTest {
            // Given
            val teamId = "integrity-test"
            val originalTeam =
                createTestTeam(
                    id = 555,
                    name = "Integrity Team",
                    iconUrl = "https://example.com/icon.png",
                    players =
                        listOf(
                            createTestPlayer(
                                id = 1,
                                name = "IntegrityPlayer",
                                firstName = "John",
                                lastName = "Doe",
                                role = "Captain",
                                nationality = "US",
                                imageUrl = "https://example.com/player.png",
                            ),
                        ),
                )
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(originalTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            val resultTeam = result.getOrThrow()
            assertEquals(originalTeam.id, resultTeam.id)
            assertEquals(originalTeam.name, resultTeam.name)
            assertEquals(originalTeam.iconUrl, resultTeam.iconUrl)
            assertEquals(originalTeam.players?.size, resultTeam.players?.size)

            val originalPlayer = originalTeam.players?.first()
            val resultPlayer = resultTeam.players?.first()
            assertEquals(originalPlayer?.id, resultPlayer?.id)
            assertEquals(originalPlayer?.name, resultPlayer?.name)
            assertEquals(originalPlayer?.firstName, resultPlayer?.firstName)
            assertEquals(originalPlayer?.lastName, resultPlayer?.lastName)
            assertEquals(originalPlayer?.role, resultPlayer?.role)
            assertEquals(originalPlayer?.nationality, resultPlayer?.nationality)
            assertEquals(originalPlayer?.imageUrl, resultPlayer?.imageUrl)
        }

    @Test
    fun `invoke handles large team with many players`() =
        runTest {
            // Given
            val teamId = "large-team"
            val players =
                (1..50).map { index ->
                    createTestPlayer(
                        id = index,
                        name = "Player$index",
                        firstName = "First$index",
                        lastName = "Last$index",
                    )
                }
            val expectedTeam =
                createTestTeam(
                    id = 666,
                    name = "Large Team",
                    players = players,
                )
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            val resultTeam = result.getOrThrow()
            assertEquals(50, resultTeam.players?.size)
            assertEquals("Player1", resultTeam.players?.first()?.name)
            assertEquals("Player50", resultTeam.players?.last()?.name)
        }

    @Test
    fun `invoke handles team with players having null fields`() =
        runTest {
            // Given
            val teamId = "null-fields"
            val playersWithNulls =
                listOf(
                    createTestPlayer(
                        id = 1,
                        name = "ValidPlayer",
                        firstName = "Valid",
                        lastName = "Player",
                        role = null,
                        imageUrl = null,
                    ),
                    createTestPlayer(
                        id = 2,
                        name = "EmptyPlayer",
                        firstName = "",
                        lastName = "",
                        role = "",
                        imageUrl = "",
                    ),
                )
            val expectedTeam =
                createTestTeam(
                    id = 777,
                    name = "Null Fields Team",
                    players = playersWithNulls,
                )
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            val resultTeam = result.getOrThrow()
            assertEquals(2, resultTeam.players?.size)

            resultTeam.players?.get(0)?.let { firstPlayer ->
                assertEquals("ValidPlayer", firstPlayer.name)
                assertEquals("Valid", firstPlayer.firstName)
                assertEquals("Player", firstPlayer.lastName)
                assertEquals(null, firstPlayer.role)
                assertEquals(null, firstPlayer.imageUrl)
            }

            resultTeam.players?.get(1)?.let { secondPlayer ->
                assertEquals("EmptyPlayer", secondPlayer.name)
                assertEquals("", secondPlayer.firstName)
                assertEquals("", secondPlayer.lastName)
                assertEquals("", secondPlayer.role)
                assertEquals("", secondPlayer.imageUrl)
            }
        }

    @Test
    fun `invoke handles different error types from repository`() =
        runTest {
            // Given
            val teamId = "error-test"
            val networkError = java.net.UnknownHostException("No internet connection")
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.failure(networkError)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isFailure)
            assertTrue(result.exceptionOrNull() is java.net.UnknownHostException)
            assertEquals("No internet connection", result.exceptionOrNull()?.message)
        }

    @Test
    fun `invoke passes correct team id to repository`() =
        runTest {
            // Given
            val teamId = "specific-id-123"
            val expectedTeam = createTestTeam(id = 123, name = "Specific Team")
            coEvery { teamsDetailsRepository.getTeamDetails(teamId) } returns Result.success(expectedTeam)

            // When
            val result = matchDetailsUseCase(teamId)

            // Then
            assertTrue(result.isSuccess)
            coVerify(exactly = 1) {
                teamsDetailsRepository.getTeamDetails(teamId)
            }
        }

    // Helper functions
    private fun createTestTeam(
        id: Int,
        name: String,
        iconUrl: String = "https://example.com/team$id.png",
        players: List<Player> = emptyList(),
    ): Team =
        Team(
            id = id,
            name = name,
            iconUrl = iconUrl,
            players = players,
        )

    private fun createTestPlayer(
        id: Int,
        name: String = "TestPlayer$id",
        slug: String = "test-player-$id",
        active: Boolean = true,
        role: String? = "Player",
        modifiedAt: String = "2023-01-01T00:00:00Z",
        firstName: String = "First$id",
        lastName: String = "Last$id",
        nationality: String = "US",
        imageUrl: String? = "https://example.com/player$id.png",
    ): Player =
        Player(
            id = id,
            name = name,
            slug = slug,
            active = active,
            role = role,
            modifiedAt = modifiedAt,
            firstName = firstName,
            lastName = lastName,
            nationality = nationality,
            imageUrl = imageUrl,
        )
}
