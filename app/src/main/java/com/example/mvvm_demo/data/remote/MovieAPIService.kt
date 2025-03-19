package com.example.mvvm_demo.data.remote

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieAPIService {
    @GET("movie")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYzcwZDc3NDg1OWJhNDVhMTUwYjI5OTdiOGQ3Y2I1ZSIsIm5iZiI6MTczODgzMjM0My4wMDMsInN1YiI6IjY3YTQ3OWQ2YmYzNjA0MGM1ZDg1YmQ4NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.MYT_GJ-iI9YQVxpPVyM0t-80Qmp7JkTDy-_XQ8LwMoQ")
    suspend fun getMovies(): MoviesResponse

}