package com.sahalnazar.paging3_retrofit_roomdb.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sahalnazar.paging3_retrofit_roomdb.data.db.AppDatabase
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.model.RemoteKeys

@OptIn(ExperimentalPagingApi::class)
class AppRemoteMediator(
    private val appApis: AppApis,
    private val db: AppDatabase
) : RemoteMediator<Int, MovieListResponse.Movie>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieListResponse.Movie>
    ): MediatorResult {

        return try {
            val key = when (loadType) {
                LoadType.REFRESH -> {
                    if (db.remoteMoviesDao().getCount() > 0) return MediatorResult.Success(false)
                    null
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    getKeyFromDb()
                }
            }

            if (key != null) if (key.isEndReached) return MediatorResult.Success(endOfPaginationReached = true)

            val page: Int = key?.nextKey ?: 1
            val apiResponse = appApis.fetchMovieList(page = page.toString(), region = "US")
            val movieList = apiResponse.movies
            val endOfPaginationReached = apiResponse.page == apiResponse.totalPages

            movieList?.let {
                db.withTransaction {
                    db.remoteKeysDao().insert(
                        RemoteKeys(
                            0,
                            nextKey = page.plus(1),
                            isEndReached = endOfPaginationReached
                        )
                    )
                    db.remoteMoviesDao().insertMovies(movieList)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyFromDb(): RemoteKeys? {
        return db.remoteKeysDao().getKeys().firstOrNull()
    }
}