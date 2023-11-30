package com.example.movie_kmm.data.repository

import com.example.movie_kmm.data.mappers.toMovie
import com.example.movie_kmm.data.data_source.RemoteDataSource
import com.example.movie_kmm.domain.model.Movie
import com.example.movie_kmm.domain.repository.MovieRepository

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): MovieRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page).results.map { it.toMovie() }
    }

    override suspend fun getMovieById(id: Int): Movie {
        return remoteDataSource.getMovieById(id).toMovie()
    }
}