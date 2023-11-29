package com.example.movie_kmm.domain.repository

import com.example.movie_kmm.domain.model.Movie
import com.example.movie_kmm.domain.model.Movies

internal interface MovieRepository {

    suspend fun getMovies(page: Int): Movies

    suspend fun getMovieById(id: Int): Movie
}