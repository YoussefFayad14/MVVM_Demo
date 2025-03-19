package com.example.mvvm_demo.data.repo

import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getAllMovies(): Flow<Response<List<Movie>>>
    fun getFavoriteMovies(): Flow<Response<List<Movie>>>
    suspend fun addMovie(movie: Movie): Long
    suspend fun removeMovie(movie: Movie): Int

}