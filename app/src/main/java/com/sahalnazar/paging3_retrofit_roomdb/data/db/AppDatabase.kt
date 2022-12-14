package com.sahalnazar.paging3_retrofit_roomdb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahalnazar.paging3_retrofit_roomdb.data.db.dao.MovieDetailDao
import com.sahalnazar.paging3_retrofit_roomdb.data.db.dao.MoviesDao
import com.sahalnazar.paging3_retrofit_roomdb.data.db.dao.RemoteKeysDao
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieDetailResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.model.RemoteKeys

@Database(
    entities = [MovieListResponse.Movie::class, RemoteKeys::class, MovieDetailResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun remoteKeysDao(): RemoteKeysDao

    abstract fun remoteMoviesDao(): MoviesDao

    abstract fun remoteMovieDetailsDao(): MovieDetailDao

}