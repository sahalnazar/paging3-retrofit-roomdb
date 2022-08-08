package com.sahalnazar.paging3_retrofit_roomdb.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sahalnazar.paging3_retrofit_roomdb.data.db.AppDatabase
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse
import com.sahalnazar.paging3_retrofit_roomdb.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appApis: AppApis,
    private val db: AppDatabase
) {

    companion object {
        const val PAGE_SIZE = 30
    }

    suspend fun fetMovieDetail(
        movieId: String
    ) = safeApiCall {
        appApis.fetchMovieDetail(
            movieId = movieId
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getMovies(): Flow<PagingData<MovieListResponse.Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            remoteMediator = AppRemoteMediator(appApis = appApis, db = db),
            pagingSourceFactory = { db.remoteMoviesDao().getMovies() }
        ).flow
    }

}