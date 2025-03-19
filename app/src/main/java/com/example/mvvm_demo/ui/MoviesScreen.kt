package com.example.mvvm_demo.ui

import MovieItem
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import com.example.mvvm_demo.viewmodel.allmovies.MoviesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviesScreen(viewModel: MoviesViewModel) {
    val moviesState = viewModel.movies.collectAsStateWithLifecycle()
    val messageState = viewModel.message.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val state = moviesState.value
            when (state) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }

                is Response.Success<*> -> {
                    val movies = state.data as? List<Movie> ?: emptyList()
                    LazyColumn {
                        items(movies) { movie ->
                            MovieItem(movie, "Favorite") {
                                Log.d("MoviesScreen", "Movie: ${movie}")
                                viewModel.addToFavorites(movie)
                                scope.launch {
                                    val message = messageState.value
                                    if (message.isNotBlank()) {
                                        snackbarHostState.showSnackbar(
                                            message = message,
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                is Response.Failure -> {
                    val errorMessage = state.error.message ?: "Unknown error"
                    Text(
                        text = "Error: $errorMessage",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
