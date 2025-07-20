package com.orafaelsc.cstvfuze.data.repository

import com.orafaelsc.cstvfuze.data.remote.MatchesApi
import com.orafaelsc.cstvfuze.data.remote.dto.LeagueDto
import com.orafaelsc.cstvfuze.data.remote.dto.MatchDto
import com.orafaelsc.cstvfuze.data.remote.dto.OpponentDto
import com.orafaelsc.cstvfuze.data.remote.dto.SerieDto
import com.orafaelsc.cstvfuze.data.remote.dto.TeamDto
import com.orafaelsc.cstvfuze.data.remote.toDomain
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class MatchesRepositoryTest {
    private lateinit var matchesApi: MatchesApi
    private lateinit var matchesRepository: MatchesRepositoryImpl

    private val sampleMatchDto =
        MatchDto(
            id = 1,
            name = "Team A vs Team B",
            status = "running",
            beginAt = "2025-07-20T15:00:00Z",
            league =
                LeagueDto(
                    id = 1,
                    name = "CS:GO Major",
                    imageUrl = "https://example.com/league.png",
                ),
            serie =
                SerieDto(
                    id = 1,
                    fullName = "Major Championship 2025",
                ),
            opponents =
                listOf(
                    OpponentDto(
                        opponent =
                            TeamDto(
                                id = 1,
                                name = "Team A",
                                imageUrl = "https://example.com/team-a.png",
                            ),
                    ),
                    OpponentDto(
                        opponent =
                            TeamDto(
                                id = 2,
                                name = "Team B",
                                imageUrl = "https://example.com/team-b.png",
                            ),
                    ),
                ),
        )

    private val sampleMatch = sampleMatchDto.toDomain()

    @Before
    fun setUp() {
        matchesApi = mockk()
        matchesRepository = MatchesRepositoryImpl(matchesApi)
    }

    @Test
    fun `getMatches returns mapped matches when API call is successful`() =
        runTest {
            // Given
            val matchDtos = listOf(sampleMatchDto)
            coEvery { matchesApi.getMatches(any(), any()) } returns Response.success(matchDtos)

            // When
            val result = matchesRepository.getMatches().first()

            // Then
            assert(result.size == 1)
            assertEquals(result.first(), sampleMatch)
            coVerify { matchesApi.getMatches() }
        }

    @Test
    fun `getMatches returns empty list when API returns empty response`() =
        runTest {
            // Given
            coEvery { matchesApi.getMatches(any(), any()) } returns Response.success(emptyList())

            // When
            val result = matchesRepository.getMatches().first()

            // Then
            assertTrue(result.isEmpty())
            coVerify { matchesApi.getMatches() }
        }

    //
    @Test
    fun `getMatches throws exception when API call fails`() =
        runTest {
            // Given
            val errorMessage = "Network error"
            coEvery { matchesApi.getMatches(any(), any()) } throws RuntimeException(errorMessage)

            // When & Then
            assertFailsWith<RuntimeException> {
                matchesRepository.getMatches().first()
            }

            coVerify { matchesApi.getMatches(any(), any()) }
        }

    @Test
    fun `getMatches handles HTTP error response correctly`() =
        runTest {
            // Given
            coEvery { matchesApi.getMatches(any(), any()) } returns
                Response.error(
                    404,
                    mockk(relaxed = true),
                )

            // When & Then
            assertFailsWith<Exception> {
                matchesRepository.getMatches().first()
            }
            coVerify { matchesApi.getMatches() }
        }

    @Test
    fun `repository handles null values in DTO mapping correctly`() =
        runTest {
            // Given
            val matchDtoWithNulls =
                MatchDto(
                    id = 1,
                    name = "Test Match",
                    status = "running",
                    beginAt = "2025-07-20T15:00:00Z",
                    league = LeagueDto(id = 1, name = "Test League", imageUrl = null),
                    serie = SerieDto(id = 1, fullName = "Test Serie"),
                    opponents = emptyList(),
                )
            coEvery { matchesApi.getMatches(any(), any()) } returns
                Response.success(
                    listOf(
                        matchDtoWithNulls,
                    ),
                )

            // When
            val result = matchesRepository.getMatches().first()

            // Then
            assertTrue(result.size == 1)
            val match = result.first()
            assertTrue(match.id == 1)
            assertEquals(match.description, "Test League - Test Serie")
        }

    @Test
    fun `repository handles pagination parameters correctly`() =
        runTest {
            // Given
            coEvery { matchesApi.getMatches(any(), any()) } returns Response.success(emptyList())

            // When
            matchesRepository.getMatches().first()

            // Then
            coVerify { matchesApi.getMatches() }
        }

    @Test
    fun `repository handles different match statuses correctly`() =
        runTest {
            // Given
            val matchDtos =
                listOf(
                    sampleMatchDto.copy(id = 1, status = "not_started"),
                    sampleMatchDto.copy(id = 2, status = "running"),
                    sampleMatchDto.copy(id = 3, status = "finished"),
                    sampleMatchDto.copy(id = 4, status = "unknown status"),
                )
            coEvery { matchesApi.getMatches(any(), any()) } returns Response.success(matchDtos)

            // When
            val result = matchesRepository.getMatches().first()

            // Then
            assertTrue(result.size == 4)
            assertEquals(result[0].status, MatchStatus.NOT_STARTED)
            assertEquals(result[1].status, MatchStatus.RUNNING)
            assertEquals(result[2].status, MatchStatus.FINISHED)
            assertEquals(result[3].status, MatchStatus.UNKNOWN)
        }
}
