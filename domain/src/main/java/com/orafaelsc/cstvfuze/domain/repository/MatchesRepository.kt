package com.orafaelsc.cstvfuze.domain.repository

import com.orafaelsc.cstvfuze.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getMatches(): Flow<List<Match>>
}
