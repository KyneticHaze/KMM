package com.example.movie_kmm.android.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movie_kmm.android.components.MovieAppBar
import com.example.movie_kmm.android.util.Detail
import com.example.movie_kmm.android.util.Home
import com.example.movie_kmm.android.util.movieDestinations
import com.example.movie_kmm.android.viewmodel.DetailScreenViewModel
import com.example.movie_kmm.android.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = Home.route
        ) {
            composable(Home.routeWithArgs) {
                val homeScreenViewModel: HomeScreenViewModel = koinViewModel()
                HomeScreen(
                    uiState = homeScreenViewModel.uiState,
                    loadNextMovies = { homeScreenViewModel.loadMovies(forceReload = it) },
                    navigateToDetail = { navController.navigate("${Detail.route}/${it.id}") }
                )
            }
            composable(Detail.routeWithArgs + Detail.arguments) {
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailViewModel: DetailScreenViewModel = koinViewModel(
                    parameters = {
                        parametersOf(movieId)
                    }
                )
                DetailScreen(uiState = detailViewModel.uiState)
            }
        }
    }
}