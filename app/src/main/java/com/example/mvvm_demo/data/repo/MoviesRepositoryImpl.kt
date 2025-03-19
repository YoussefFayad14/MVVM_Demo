package com.example.mvvm_demo.data.repo

import com.example.mvvm_demo.data.remote.RemoteDataSource
import com.example.mvvm_demo.data.local.LocalDataSource
import com.example.mvvm_demo.data.models.Movie
import com.example.mvvm_demo.data.models.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
     private val localDataSource: LocalDataSource
) : MoviesRepository {

        override fun getAllMovies(): Flow<Response<List<Movie>>> {
            return remoteDataSource.getAllMovies()
        }

        override fun getFavoriteMovies(): Flow<Response<List<Movie>>> {
            return localDataSource.getAllMovies()
        }

        override suspend fun addMovie(movie: Movie): Long {
            return localDataSource.insertMovie(movie)
        }

        override suspend fun removeMovie(movie: Movie): Int {
            return localDataSource.deleteMovie(movie)

        }
    companion object {
        @Volatile
        private var instance: MoviesRepositoryImpl? = null
        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource):MoviesRepositoryImpl {
            return instance ?: synchronized(this) {
                val INSTANCE = MoviesRepositoryImpl(remoteDataSource, localDataSource)
                instance = INSTANCE
                INSTANCE
            }
        }
    }

}