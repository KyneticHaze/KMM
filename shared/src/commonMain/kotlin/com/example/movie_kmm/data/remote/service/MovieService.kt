package com.example.movie_kmm.data.remote.service

import com.example.movie_kmm.data.remote.dto.MovieDTO
import com.example.movie_kmm.data.remote.dto.Result
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieService: KtorApi() {

    suspend fun getMovies(page: Int = 1): MovieDTO = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovieById(movieId: Int): Result = client.get {
        pathUrl("movie/${movieId}")
    }.body()
}