package com.example.movie_kmm.data.mappers

import com.example.movie_kmm.data.remote.dto.MovieDTO
import com.example.movie_kmm.data.remote.dto.Result
import com.example.movie_kmm.domain.model.Movie

internal fun Result.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = getImageUrl(posterPath),
        releaseDate = releaseDate
    )
}

private fun getImageUrl(posterPath: String) = "https://www.themoviedb.org/t/p/w500/${posterPath}"