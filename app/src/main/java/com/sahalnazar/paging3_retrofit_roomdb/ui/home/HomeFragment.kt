package com.sahalnazar.paging3_retrofit_roomdb.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sahalnazar.paging3_retrofit_roomdb.R
import com.sahalnazar.paging3_retrofit_roomdb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        _binding = binding

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}