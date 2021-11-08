package com.grupo7.brflixapp.application

import android.app.Application
import com.grupo7.brflixapp.di.AppComponent.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(allModules)
        }
    }
}