package com.example.mvvm_demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvvm_demo.ui.MoviesScreen
import com.example.mvvm_demo.data.local.MoviesDatabase
import com.example.mvvm_demo.data.local.MoviesLocalDataSource
import com.example.mvvm_demo.data.remote.MoviesRemoteDataSource
import com.example.mvvm_demo.data.remote.RetrofitHelper
import com.example.mvvm_demo.data.repo.MoviesRepositoryImpl
import com.example.mvvm_demo.ui.FavoriteMoviesScreen
import com.example.mvvm_demo.ui.viewmodel.MoviesViewModelFactory
import com.example.mvvm_demo.viewmodel.allmovies.MoviesViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val context = LocalContext.current
    val moviesViewModel: MoviesViewModel = viewModel(
        factory = MoviesViewModelFactory(
            MoviesRepositoryImpl.getInstance(
                MoviesRemoteDataSource(RetrofitHelper.service),
                MoviesLocalDataSource(MoviesDatabase.getInstance(context).getMoviesDao())
            )
        )
    )

    NavHost(navController, startDestination = "home") {
        composable("home") { MainScreen(navController) }
        composable("movies") { MoviesScreen(moviesViewModel) }
        composable("favorites") { FavoriteMoviesScreen(moviesViewModel) }
    }
}
