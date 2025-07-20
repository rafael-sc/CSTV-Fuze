package com.orafaelsc.cstvfuze.domain.usecase

import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.model.Team
import com.orafaelsc.cstvfuze.domain.repository.MatchesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MatchesUseCaseTest {
    private lateinit var matchesRepository: MatchesRepository
    private lateinit var matchesUseCase: MatchesUseCase

    @Before
    fun setUp() {
        matchesRepository = mockk()
        matchesUseCase = MatchesUseCase(matchesRepository)
    }

    @Test
    fun `useCase returns empty list when repository returns empty list`() =
        runTest {
            // Given
            coEvery { matchesRepository.getMatches() } returns flowOf(emptyList())

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(emptyList<Match>(), result)
        }

    @Test
    fun `useCase returns single match when repository returns single match`() =
        runTest {
            // Given
            val match =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T10:00:00Z",
                )
            coEvery { matchesRepository.getMatches() } returns flowOf(listOf(match))

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(listOf(match), result)
        }

    @Test
    fun `useCase sorts RUNNING matches before NOT_STARTED matches`() =
        runTest {
            // Given
            val notStartedMatch =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T10:00:00Z",
                )
            val runningMatch =
                createTestMatch(
                    id = 2,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T12:00:00Z",
                )
            coEvery { matchesRepository.getMatches() } returns
                flowOf(
                    listOf(
                        notStartedMatch,
                        runningMatch,
                    ),
                )

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(listOf(runningMatch, notStartedMatch), result)
        }

    @Test
    fun `useCase sorts RUNNING matches before FINISHED matches`() =
        runTest {
            // Given
            val finishedMatch =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.FINISHED,
                    startTime = "2025-07-20T10:00:00Z",
                )
            val runningMatch =
                createTestMatch(
                    id = 2,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T12:00:00Z",
                )
            coEvery { matchesRepository.getMatches() } returns
                flowOf(
                    listOf(
                        finishedMatch,
                        runningMatch,
                    ),
                )

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(listOf(runningMatch, finishedMatch), result)
        }

    @Test
    fun `useCase sorts multiple RUNNING matches by start time ascending`() =
        runTest {
            // Given
            val runningMatch1 =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T12:00:00Z",
                )
            val runningMatch2 =
                createTestMatch(
                    id = 2,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T10:00:00Z",
                )
            val runningMatch3 =
                createTestMatch(
                    id = 3,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T14:00:00Z",
                )
            coEvery { matchesRepository.getMatches() } returns
                flowOf(
                    listOf(runningMatch1, runningMatch3, runningMatch2),
                )

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(listOf(runningMatch2, runningMatch1, runningMatch3), result)
        }

    @Test
    fun `useCase sorts multiple NOT_STARTED matches by start time ascending`() =
        runTest {
            // Given
            val notStartedMatch1 =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T12:00:00Z",
                )
            val notStartedMatch2 =
                createTestMatch(
                    id = 2,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T10:00:00Z",
                )
            val notStartedMatch3 =
                createTestMatch(
                    id = 3,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T14:00:00Z",
                )
            coEvery { matchesRepository.getMatches() } returns
                flowOf(
                    listOf(notStartedMatch1, notStartedMatch3, notStartedMatch2),
                )

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(listOf(notStartedMatch2, notStartedMatch1, notStartedMatch3), result)
        }

    @Test
    fun `useCase sorts mixed status matches correctly - RUNNING first then by start time`() =
        runTest {
            // Given
            val notStartedEarly =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T09:00:00Z",
                )
            val runningLate =
                createTestMatch(
                    id = 2,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T15:00:00Z",
                )
            val runningEarly =
                createTestMatch(
                    id = 3,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T11:00:00Z",
                )
            val notStartedLate =
                createTestMatch(
                    id = 4,
                    status = MatchStatus.NOT_STARTED,
                    startTime = "2025-07-20T13:00:00Z",
                )
            val finishedMatch =
                createTestMatch(
                    id = 5,
                    status = MatchStatus.FINISHED,
                    startTime = "2025-07-20T08:00:00Z",
                )

            coEvery { matchesRepository.getMatches() } returns
                flowOf(
                    listOf(notStartedEarly, runningLate, finishedMatch, runningEarly, notStartedLate),
                )

            // When
            val result = matchesUseCase().first()

            // Then
            val expected =
                listOf(
                    runningEarly, // RUNNING + earliest time
                    runningLate, // RUNNING + later time
                    finishedMatch, // FINISHED + earliest time
                    notStartedEarly, // NOT_STARTED + earlier time
                    notStartedLate, // NOT_STARTED + later time
                )
            assertEquals(expected, result)
        }

    @Test
    fun `useCase handles matches with same start time and same status`() =
        runTest {
            // Given
            val match1 =
                createTestMatch(
                    id = 1,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T10:00:00Z",
                )
            val match2 =
                createTestMatch(
                    id = 2,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T10:00:00Z",
                )
            coEvery { matchesRepository.getMatches() } returns flowOf(listOf(match1, match2))

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(2, result.size)
            assertEquals(MatchStatus.RUNNING, result[0].status)
            assertEquals(MatchStatus.RUNNING, result[1].status)
        }

    @Test
    fun `useCase handles large number of matches efficiently`() =
        runTest {
            // Given
            val matches =
                (1..100).map { index ->
                    createTestMatch(
                        id = index,
                        status = if (index % 3 == 0) MatchStatus.RUNNING else MatchStatus.NOT_STARTED,
                        startTime = "2025-07-20T${10 + (index % 10)}:00:00Z",
                    )
                }
            coEvery { matchesRepository.getMatches() } returns flowOf(matches)

            // When
            val result = matchesUseCase().first()

            // Then
            assertEquals(100, result.size)
            // Verify RUNNING matches come first
            val runningCount = result.takeWhile { it.status == MatchStatus.RUNNING }.size
            val expectedRunningCount = matches.count { it.status == MatchStatus.RUNNING }
            assertEquals(expectedRunningCount, runningCount)
        }

    @Test
    fun `useCase preserves all match properties during sorting`() =
        runTest {
            // Given
            val originalMatch =
                createTestMatch(
                    id = 42,
                    status = MatchStatus.RUNNING,
                    startTime = "2025-07-20T10:00:00Z",
                    description = "Test Match Description",
                )
            coEvery { matchesRepository.getMatches() } returns flowOf(listOf(originalMatch))

            // When
            val result = matchesUseCase().first()

            // Then
            val resultMatch = result.first()
            assertEquals(originalMatch.id, resultMatch.id)
            assertEquals(originalMatch.status, resultMatch.status)
            assertEquals(originalMatch.startTime, resultMatch.startTime)
            assertEquals(originalMatch.description, resultMatch.description)
            assertEquals(originalMatch.firstTeam, resultMatch.firstTeam)
            assertEquals(originalMatch.secondTeam, resultMatch.secondTeam)
        }

    // Helper function to create test matches
    private fun createTestMatch(
        id: Int,
        status: MatchStatus,
        startTime: String,
        description: String = "Test Match $id",
    ): Match =
        Match(
            id = id,
            status = status,
            startTime = startTime,
            description = description,
            firstTeam =
                Team(
                    id = id * 10,
                    name = "Team ${id}A",
                    iconUrl = "https://example.com/team${id}a.png",
                    players = emptyList(),
                ),
            secondTeam =
                Team(
                    id = id * 10 + 1,
                    name = "Team ${id}B",
                    iconUrl = "https://example.com/team${id}b.png",
                    players = emptyList(),
                ),
            leagueLogo = "https://example.com/logo${id}league.png",
        )
}
