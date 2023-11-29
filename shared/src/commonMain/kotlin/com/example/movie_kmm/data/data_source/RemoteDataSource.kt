package com.example.movie_kmm.data.data_source

import com.example.movie_kmm.data.remote.service.MovieService
import com.example.movie_kmm.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {
    suspend fun getMovies(page: Int) = withContext(dispatcher.io) {
        apiService.getMovies(page)
    }

    suspend fun getMovieById(id: Int) = withContext(dispatcher.io) {
        apiService.getMovieById(id)
    }
}