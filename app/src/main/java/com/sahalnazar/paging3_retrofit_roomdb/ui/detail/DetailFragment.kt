package com.sahalnazar.paging3_retrofit_roomdb.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.sahalnazar.paging3_retrofit_roomdb.BuildConfig
import com.sahalnazar.paging3_retrofit_roomdb.R
import com.sahalnazar.paging3_retrofit_roomdb.databinding.FragmentDetailBinding
import com.sahalnazar.paging3_retrofit_roomdb.util.BaseResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        _binding = binding

        viewModel.fetchMovieDetails(args.movieId)

        initUi()
    }

    private fun initUi() {
        viewModel.movieDetails.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                BaseResult.Status.ERROR -> {
                    _binding?.progressBar2?.isVisible = false
                    Toast.makeText(context, "Error: ${response.message}", Toast.LENGTH_SHORT).show()
                }
                BaseResult.Status.LOADING -> {
                    _binding?.progressBar2?.isVisible = true
                }
                BaseResult.Status.SUCCESS -> {
                    val data = response.data
                    _binding?.apply {
                        progressBar2.isVisible = false
                        ivBanner.load(BuildConfig.BASE_IMG_URL + data?.backdropPath)
                        tvTitle.text = data?.title ?: ""
                        tvSubTitle.text = data?.tagline ?: ""
                        tvPopularity.text = data?.popularity?.toString() ?: ""
                        tvRelDate.text = data?.releaseDate ?: ""
                        tvRevenue.text = data?.revenue?.let { "USD $it" } ?: ""
                        tvOverview.text = data?.overview ?: ""
                    }


                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}