package com.example.movie_kmm.android.util

import com.example.movie_kmm.domain.model.Movies

data class HomeScreenState(
    val isLoading: Boolean = false,
    val refreshing: Boolean = false,
    val moviePageList: Movies = Movies(page = 1, emptyList()),
    val errorMessage: String = "",
    val loadFinished: Boolean = false
)
