package com.example.movie_kmm.android

import android.app.Application
import com.example.movie_kmm.android.di.appModule
import com.example.movie_kmm.di.getSharedModules
import org.koin.core.context.startKoin

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}