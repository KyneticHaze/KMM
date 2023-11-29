package com.example.movie_kmm.domain.use_case

import com.example.movie_kmm.domain.model.Movies
import com.example.movie_kmm.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase: KoinComponent {

    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): Movies {
        return repository.getMovies(page)
    }
}