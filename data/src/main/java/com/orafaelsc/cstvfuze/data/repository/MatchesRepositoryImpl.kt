package com.orafaelsc.cstvfuze.data.repository
import com.orafaelsc.cstvfuze.data.remote.MatchesApi
import com.orafaelsc.cstvfuze.data.remote.toDomain
import com.orafaelsc.cstvfuze.domain.model.Match
import com.orafaelsc.cstvfuze.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MatchesRepositoryImpl(
    private val matchesAPI: MatchesApi,
) : MatchesRepository {
    // Signature remains the same: Flow<List<Match>>
    override suspend fun getMatches(): Flow<List<Match>> =
        flow {
            val response = matchesAPI.getMatches()
            if (response.isSuccessful) {
                val domainMatches = response.body()?.map { it.toDomain() } ?: emptyList()
                emit(domainMatches) // Emit the success data directly
            } else {
                // If there's an error, THROW an exception.
                // This terminates the flow in an error state.
                throw Exception("API Error: ${response.code()}")
            }
        }
}
