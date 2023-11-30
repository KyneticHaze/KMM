package com.example.movie_kmm.android.util

import com.example.movie_kmm.domain.model.Movie

data class DetailScreenState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val errorMessage: String? = null
)
