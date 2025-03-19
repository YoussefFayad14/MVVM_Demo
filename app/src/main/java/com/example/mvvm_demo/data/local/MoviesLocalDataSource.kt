package com.example.mvvm_demo.data.local

import android.util.Log
import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesLocalDataSource(private val dao: MoviesDao): LocalDataSource {
    override suspend fun insertMovie(movie: Movie): Long {
        return dao.insertMovie(movie)
    }
    override suspend fun deleteMovie(movie: Movie): Int {
        return if (movie != null) dao.deleteMovie(movie)
        else -1
    }
    override fun getAllMovies(): Flow<Response<List<Movie>>> = flow {
        try {
            emit(Response.Loading)
            dao.getAllMovies().collect {
                emit(Response.Success(it))
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}