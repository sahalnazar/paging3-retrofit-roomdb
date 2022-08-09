package com.sahalnazar.paging3_retrofit_roomdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahalnazar.paging3_retrofit_roomdb.databinding.ItemMovieStateBinding

class MovieFetchStateAdapter() : LoadStateAdapter<MovieFetchStateAdapter.MovieFetchStateViewHolder>() {

    inner class MovieFetchStateViewHolder(private val binding: ItemMovieStateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Loading -> {
                    binding.tvFetchState.text = "Loading"
                    binding.progressBar.isVisible = true
                }
                is LoadState.Error -> {
                    binding.tvFetchState.text = "Error: ${loadState.error}"
                    binding.progressBar.isVisible = false
                }
                is LoadState.NotLoading -> {
                    if (loadState.endOfPaginationReached) {
                        binding.tvFetchState.text = "That's it. End of of the list"
                        binding.progressBar.isVisible = false
                    } else {
                        binding.progressBar.isVisible = false
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MovieFetchStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MovieFetchStateViewHolder {
        return MovieFetchStateViewHolder(
            ItemMovieStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}