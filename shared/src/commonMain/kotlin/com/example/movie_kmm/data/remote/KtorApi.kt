package com.example.movie_kmm.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// https://api.themoviedb.org/3/movie/popular?page=12&api_key=3fda8f3cc3ee174703991877b9a0ec39

// https://api.themoviedb.org/3/movie/157336?api_key=3fda8f3cc3ee174703991877b9a0ec39

const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "3fda8f3cc3ee174703991877b9a0ec39"

internal abstract class KtorApi {

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path("3", path)
            parameter("api_key", API_KEY)
        }
    }
}