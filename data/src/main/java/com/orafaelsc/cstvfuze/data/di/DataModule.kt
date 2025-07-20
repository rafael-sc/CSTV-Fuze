package com.orafaelsc.cstvfuze.data.di

import com.orafaelsc.cstvfuze.data.repository.MatchesRepositoryImpl
import com.orafaelsc.cstvfuze.data.repository.TeamDetailsRepositoryImpl
import com.orafaelsc.cstvfuze.domain.repository.MatchesRepository
import com.orafaelsc.cstvfuze.domain.repository.TeamDetailsRepository
import org.koin.dsl.module

val dataModule =
    module {
        single<MatchesRepository> {
            MatchesRepositoryImpl(
                matchesAPI = get(),
            )
        }
        single<TeamDetailsRepository> {
            TeamDetailsRepositoryImpl(
                teamApi = get(),
            )
        }
    }
