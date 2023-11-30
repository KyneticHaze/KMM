package com.example.movie_kmm.android.util

import com.example.movie_kmm.domain.model.Movie

data class HomeScreenState(
    val isLoading: Boolean = false,
    val refreshing: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String = "",
    val loadFinished: Boolean = false
)
