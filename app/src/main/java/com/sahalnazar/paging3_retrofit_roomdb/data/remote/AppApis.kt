package com.sahalnazar.paging3_retrofit_roomdb.data.remote

import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieDetailResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApis {

    @GET("movie/now_playing")
    suspend fun fetchMovieList(
        @Query("page") page: String,
        @Query("region") region: String,
    ): MovieListResponse

    @GET("movie/{movieId}")
    suspend fun fetchMovieDetail(
        @Path(value = "movieId", encoded = true) movieId: String,
    ): Response<MovieDetailResponse>

}