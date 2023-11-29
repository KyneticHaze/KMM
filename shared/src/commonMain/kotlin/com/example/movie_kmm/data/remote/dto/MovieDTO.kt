package com.example.movie_kmm.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDTO(
    val page: Int,
    val results: List<Result>,
    @SerialName(value = "total_pages")
    val totalPages: Int,
    @SerialName(value = "total_results")
    val totalResults: Int
)