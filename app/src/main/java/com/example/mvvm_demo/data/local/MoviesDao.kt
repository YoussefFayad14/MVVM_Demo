package com.example.mvvm_demo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.example.mvvm_demo.data.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: Movie): Long

    @Delete
    suspend fun deleteMovie(movie: Movie): Int

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): Flow<List<Movie>>

}