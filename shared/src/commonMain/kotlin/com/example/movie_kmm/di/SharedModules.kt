package com.example.movie_kmm.di

import com.example.movie_kmm.data.remote.service.MovieService
import com.example.movie_kmm.data.data_source.RemoteDataSource
import com.example.movie_kmm.data.repository.MovieRepositoryImpl
import com.example.movie_kmm.domain.repository.MovieRepository
import com.example.movie_kmm.domain.use_case.GetMovieByIdUseCase
import com.example.movie_kmm.domain.use_case.GetMoviesUseCase
import com.example.movie_kmm.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory {
        RemoteDataSource(get(), get())
    }
    factory {
        MovieService()
    }
}

private val utilModule = module {
    factory {
        provideDispatcher()
    }
}

private val domainModule = module {
    factory {
        GetMoviesUseCase()
    }
    factory {
        GetMovieByIdUseCase()
    }
    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }
}

private val sharedModules = listOf(dataModule, utilModule, domainModule)

fun getSharedModules() = sharedModules