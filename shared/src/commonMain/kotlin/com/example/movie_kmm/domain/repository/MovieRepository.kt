package com.example.movie_kmm.domain.repository

import com.example.movie_kmm.domain.model.Movie

internal interface MovieRepository {

    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovieById(id: Int): Movie
}