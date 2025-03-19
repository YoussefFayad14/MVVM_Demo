package com.example.mvvm_demo.data.local

import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertMovie(movie: Movie): Long
    suspend fun deleteMovie(movie: Movie): Int
    fun getAllMovies(): Flow<Response<List<Movie>>>
}