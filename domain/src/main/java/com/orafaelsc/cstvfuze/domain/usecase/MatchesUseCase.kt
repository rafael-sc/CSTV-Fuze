package com.orafaelsc.cstvfuze.domain.usecase

import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.model.MatchStatus
import com.orafaelsc.cstvfuze.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * This use case fetches the list of matches and applies the core business logic
 * of sorting them to ensure "RUNNING" matches are always displayed first.
 */
class MatchesUseCase(
    private val matchesRepository: MatchesRepository,
) {
    /**
     * The `invoke` operator allows this class to be called as if it were a function.
     * e.g., `getMatchesUseCase()`
     */
    suspend operator fun invoke(): Flow<List<Match>> =
        matchesRepository.getMatches().map { matches ->
            matches.sortedWith(
                compareBy<Match> { match ->
                    match.status != MatchStatus.RUNNING
                }.thenBy { match ->
                    match.startTime
                },
            )
        }
}
