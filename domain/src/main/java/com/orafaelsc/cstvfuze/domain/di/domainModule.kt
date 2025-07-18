package com.orafaelsc.cstvfuze.domain.di

import com.orafaelsc.cstvfuze.domain.usecase.GetMatchesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetMatchesUseCase(get())
    }
}