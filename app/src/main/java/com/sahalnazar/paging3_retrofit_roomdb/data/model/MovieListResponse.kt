package com.sahalnazar.paging3_retrofit_roomdb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sahalnazar.paging3_retrofit_roomdb.data.db.Converters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val movies: List<Movie?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
) {

    @Entity(tableName = "movies_table")
    @TypeConverters(Converters.GenreIdsModel::class)
    @Serializable
    data class Movie(
        @SerialName("adult")
        val adult: Boolean? = null,
        @SerialName("backdrop_path")
        val backdropPath: String? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int?>? = null,
        @PrimaryKey(autoGenerate = false)
        @SerialName("id")
        val id: Int? = null,
        @SerialName("original_language")
        val originalLanguage: String? = null,
        @SerialName("original_title")
        val originalTitle: String? = null,
        @SerialName("overview")
        val overview: String? = null,
        @SerialName("popularity")
        val popularity: Double? = null,
        @SerialName("poster_path")
        val posterPath: String? = null,
        @SerialName("release_date")
        val releaseDate: String? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("video")
        val video: Boolean? = null,
        @SerialName("vote_average")
        val voteAverage: Double? = null,
        @SerialName("vote_count")
        val voteCount: Int? = null
    )
}