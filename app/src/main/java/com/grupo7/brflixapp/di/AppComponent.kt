package com.grupo7.brflixapp.di

import com.grupo7.brflixapp.di.DataModule.repositoryModules
import com.grupo7.brflixapp.di.DomainModule.useCaseModules
import com.grupo7.brflixapp.di.PresentationModule.viewModelModules
import org.koin.core.module.Module

object AppComponent {

    val allModules: List<Module> get() =
        listOf(
            *presentationModules,
            *dataModules,
            *domainModules
        )

    private val presentationModules: Array<Module> get() = arrayOf(viewModelModules)
    private val dataModules: Array<Module> get() = arrayOf(repositoryModules)
    private val domainModules: Array<Module> get() = arrayOf(useCaseModules)

}