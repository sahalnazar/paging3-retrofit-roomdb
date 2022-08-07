package com.sahalnazar.paging3_retrofit_roomdb.data.remote

import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieDetailResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApis {

    @GET("discover/movie")
    suspend fun fetchMovieList(
        @Query("language") language: String = "en-US",
        @Query("sort_by") sort_by: String = "popularity.desc",
        @Query("page") page: String,
    ): Response<MovieListResponse>

    @GET("movie/{movieId}")
    suspend fun fetchMovieDetail(
        @Path(value = "movieId", encoded = true) movieId: String,
    ): Response<MovieDetailResponse>

}