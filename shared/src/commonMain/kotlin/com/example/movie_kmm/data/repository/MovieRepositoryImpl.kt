package com.example.movie_kmm.data.repository

import com.example.movie_kmm.data.mappers.movieWithPage
import com.example.movie_kmm.data.mappers.toMovie
import com.example.movie_kmm.data.data_source.RemoteDataSource
import com.example.movie_kmm.domain.model.Movie
import com.example.movie_kmm.domain.model.Movies
import com.example.movie_kmm.domain.repository.MovieRepository
import io.ktor.util.logging.Logger

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): MovieRepository {
    override suspend fun getMovies(page: Int): Movies {
        return remoteDataSource.getMovies(page).movieWithPage()
    }

    override suspend fun getMovieById(id: Int): Movie {
        return remoteDataSource.getMovieById(id).toMovie()
    }
}