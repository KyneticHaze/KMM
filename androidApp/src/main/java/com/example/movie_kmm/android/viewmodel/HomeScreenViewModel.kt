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
    private var currentPage = 1

    init {
        loadMovies(false)
    }

    fun loadMovies(forceReload: Boolean = false) {

        if (uiState.isLoading) {
            return
        }

        if (forceReload) {
            currentPage = 1
        }

        if (currentPage == 1) {
            uiState = uiState.copy(
                refreshing = true
            )
        }

        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )

            try {
                val resultMovies = getMoviesUseCase(page = currentPage)

                val movies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies
                currentPage += 1
                uiState = uiState.copy(
                    isLoading = false,
                    refreshing = false,
                    loadFinished = resultMovies.isEmpty(),
                    movies = movies
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