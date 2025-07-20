package com.orafaelsc.cstvfuze.domain.di

import com.orafaelsc.cstvfuze.domain.usecase.MatchDetailsUseCase
import com.orafaelsc.cstvfuze.domain.usecase.MatchesUseCase
import org.koin.dsl.module

val domainModule =
    module {
        factory {
            MatchesUseCase(get())
        }
        factory {
            MatchDetailsUseCase(get())
        }
    }
