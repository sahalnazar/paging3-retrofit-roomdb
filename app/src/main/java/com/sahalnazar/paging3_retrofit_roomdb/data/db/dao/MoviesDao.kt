package com.sahalnazar.paging3_retrofit_roomdb.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse.Result

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(list: List<Result>)

    @Query("SELECT * FROM movies_table")
    fun getMovies(): PagingSource<Int, Result>

    @Query("SELECT COUNT(id) from movies_table")
    suspend fun getCount(): Int

}