package com.example.movie_kmm.android.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_kmm.android.util.HomeScreenState
import com.example.movie_kmm.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenState())

    init {
        loadMovies(false)
    }

    fun loadMovies(forceReload: Boolean = false) {

        if (uiState.isLoading) {
            return
        }

        if (forceReload) {
            uiState.moviePageList.page = 1
        }

        if (uiState.moviePageList.page == 1) {
            uiState = uiState.copy(
                refreshing = true
            )
        }

        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )

            try {
                val resultMovies = getMoviesUseCase(uiState.moviePageList.page)

                val movies = if (uiState.moviePageList.page == 1) resultMovies else uiState.moviePageList
                uiState.moviePageList.page += 1
                uiState = uiState.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = resultMovies.movies.isEmpty(),
                    moviePageList = movies
                )

            } catch (e: Throwable) {
                uiState = uiState.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Couldn't load: ${e.localizedMessage}"
                )
            }
        }
    }
}