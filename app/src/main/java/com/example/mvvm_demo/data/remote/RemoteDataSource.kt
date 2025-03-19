package com.example.mvvm_demo.data.remote

import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllMovies(): Flow<Response<List<Movie>>>
}
