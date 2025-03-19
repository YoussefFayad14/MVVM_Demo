package com.example.mvvm_demo.viewmodel.allmovies


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import com.example.mvvm_demo.data.repo.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _movies = MutableStateFlow<Response<List<Movie>>>(Response.Loading)
    val movies = _movies.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private val _favoriteMovies = MutableStateFlow<Response<List<Movie>>>(Response.Loading)
    val favoriteMovies = _favoriteMovies.asStateFlow()

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovies()
                .catch { e -> _message.value = "Error fetching movies, ${e.message}" }
                .collect { response -> _movies.value = response }
        }
    }


    fun fetchFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
           repository.getFavoriteMovies()
                .catch { e -> _message.value = "Error fetching favorite movies, ${e.message}" }
                .collect { response -> _favoriteMovies.value = response }
        }
    }
    fun addToFavorites(movie: Movie) {
        if(movie != null){
            viewModelScope.launch(Dispatchers.IO) {
                Log.d("MoviesViewModel", "Adding movie to favorites: $movie")
                repository.addMovie(movie)
                _message.value = "Movie added to favorites"
            }
        }else{
            _message.value = "Movie is null"
        }

    }
    fun removeFromFavorites(movie: Movie) {
        if (movie != null) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.removeMovie(movie)
                _message.value = "Movie removed from favorites"
            }
        } else {
            _message.value = "Movie is null"
        }
    }

}
