package com.example.mvvm_demo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_demo.data.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        @Volatile
        private var instance: MoviesDatabase? = null
        fun getInstance(context: Context): MoviesDatabase {
            return instance ?: synchronized(this) {
                val INSTANCE = Room.databaseBuilder(context.applicationContext, MoviesDatabase::class.java, "movies_database").build()
                instance = INSTANCE
                INSTANCE
            }
        }
    }
}
