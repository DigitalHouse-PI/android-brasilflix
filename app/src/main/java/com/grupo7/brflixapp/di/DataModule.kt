package com.grupo7.brflixapp.di

import com.grupo7.brflixapp.ui.fragments.home.repository.HomeRepository
import org.koin.dsl.module

object DataModule {

    val repositoryModules = module {
        single { HomeRepository() }

    }

}