package com.sahalnazar.paging3_retrofit_roomdb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sahalnazar.paging3_retrofit_roomdb.R
import com.sahalnazar.paging3_retrofit_roomdb.databinding.FragmentDetailBinding
import com.sahalnazar.paging3_retrofit_roomdb.databinding.FragmentHomeBinding
import com.sahalnazar.paging3_retrofit_roomdb.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val viewModel by viewModels<DetailViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        _binding = binding

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}