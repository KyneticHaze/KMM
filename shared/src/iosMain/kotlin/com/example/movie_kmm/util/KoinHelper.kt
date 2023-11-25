package com.example.movie_kmm.util

import com.example.movie_kmm.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(getSharedModules())
    }
}