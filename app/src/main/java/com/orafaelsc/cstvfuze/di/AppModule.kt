package com.orafaelsc.cstvfuze.di

import com.orafaelsc.cstvfuze.core.ResourceProvider
import com.orafaelsc.cstvfuze.ui.matches.MatchesViewModel
import com.orafaelsc.cstvfuze.util.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule =
    module {
        single<ResourceProvider> {
            ResourceProviderImpl(androidContext())
        }
        viewModel<MatchesViewModel> {
            MatchesViewModel(
                matchesUseCase = get(),
                resourceProvider = get(),
            )
        }
    }
