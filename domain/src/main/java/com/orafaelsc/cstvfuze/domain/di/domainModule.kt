package com.orafaelsc.cstvfuze.domain.di

import com.orafaelsc.cstvfuze.domain.usecase.MatchesUseCase
import org.koin.dsl.module

val domainModule =
    module {
        factory {
            MatchesUseCase(get())
        }
    }
