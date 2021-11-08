package com.grupo7.brflixapp.di

import com.grupo7.brflixapp.ui.fragments.home.usecase.HomeUseCase
import org.koin.dsl.module

object DomainModule {

    val useCaseModules = module {
        single { HomeUseCase() }

    }

}