package com.example.mvvm_demo.data.remote

import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRemoteDataSource(private val service: MovieAPIService) : RemoteDataSource {
    override fun getAllMovies(): Flow<Response<List<Movie>>> = flow {
        try {
            emit(Response.Loading)
            val movies = service.getMovies().results
            emit(Response.Success(movies))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}