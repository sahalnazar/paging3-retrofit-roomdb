package com.sahalnazar.paging3_retrofit_roomdb.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieDetailResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movie: MovieDetailResponse)

    @Query("SELECT * FROM movie_detail_table WHERE id=:movieId")
    fun getMovieDetails(movieId: String): Flow<MovieDetailResponse>

}