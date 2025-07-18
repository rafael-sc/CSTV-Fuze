package com.orafaelsc.cstvfuze.di

import com.orafaelsc.cstvfuze.MatchesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MatchesViewModel> {
        MatchesViewModel(
            matchesUseCase = get()
        )
    }
}
