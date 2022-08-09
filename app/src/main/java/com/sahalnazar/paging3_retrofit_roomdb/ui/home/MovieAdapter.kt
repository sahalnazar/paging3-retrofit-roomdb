package com.sahalnazar.paging3_retrofit_roomdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sahalnazar.paging3_retrofit_roomdb.BuildConfig
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieListResponse
import com.sahalnazar.paging3_retrofit_roomdb.databinding.ItemMovieBinding

class MovieAdapter(
    private val onClick: (movieId: String) -> Unit
) : PagingDataAdapter<MovieListResponse.Movie, MovieAdapter.MovieViewHolder>(
    object : DiffUtil.ItemCallback<MovieListResponse.Movie>() {
        override fun areItemsTheSame(
            oldItem: MovieListResponse.Movie,
            newItem: MovieListResponse.Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListResponse.Movie,
            newItem: MovieListResponse.Movie
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieListResponse.Movie) {
            binding.root.setOnClickListener {
                data.id?.toString()?.let { it1 -> onClick(it1) }
            }
            binding.ivPoster.load(BuildConfig.BASE_IMG_URL + data.posterPath)
            binding.tvRating.text = data.voteAverage?.let { "Rating: $it" } ?: "Not found"
            binding.tvReleaseDate.text = data.releaseDate?.let { "Release date: $it" } ?: "Not found"
        }

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

}