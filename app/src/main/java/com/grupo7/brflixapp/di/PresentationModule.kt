package com.grupo7.brflixapp.di

import com.grupo7.brflixapp.ui.fragments.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    val viewModelModules = module {
        viewModel { HomeViewModel() }

    }

}