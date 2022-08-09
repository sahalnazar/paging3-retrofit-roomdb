package com.sahalnazar.paging3_retrofit_roomdb.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.sahalnazar.paging3_retrofit_roomdb.R
import com.sahalnazar.paging3_retrofit_roomdb.databinding.FragmentHomeBinding
import com.sahalnazar.paging3_retrofit_roomdb.util.ExtensionFunctions.dp
import com.sahalnazar.paging3_retrofit_roomdb.util.LinearItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel>()
    private var movieAdapter: MovieAdapter? = MovieAdapter {
        onMovieItemClicked(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        _binding = binding

        setupRecyclerView(binding)


    }

    private fun setupRecyclerView(binding: FragmentHomeBinding) {
        binding.homeRecyclerView.apply {
            adapter = movieAdapter
            addItemDecoration(
                LinearItemDecorator(horizontalSpacing = 16.dp, verticalSpacing = 8.dp)
            )
        }
        viewModel.fetchNowPlayingMovies().asLiveData().observe(viewLifecycleOwner) {
            movieAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        movieAdapter = null
    }

    private fun onMovieItemClicked(movieId: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId))
    }
}