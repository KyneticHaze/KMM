package com.example.movie_kmm.android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_kmm.android.util.DetailScreenState
import com.example.movie_kmm.domain.use_case.GetMovieByIdUseCase
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    val getMovieByIdUseCase: GetMovieByIdUseCase,
    val movieId: Int
) : ViewModel() {

    var uiState by mutableStateOf(DetailScreenState())

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            uiState = try {
                val movie = getMovieByIdUseCase(movieId)
                uiState.copy(
                    isLoading = false,
                    movie = movie
                )
            } catch (e: Exception) {
                uiState.copy(
                    isLoading = false,
                    errorMessage = "Couldn't find movie!"
                )
            }
        }
    }
}