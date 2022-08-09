package com.sahalnazar.paging3_retrofit_roomdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieDetailResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.remote.AppRepository
import com.sahalnazar.paging3_retrofit_roomdb.util.BaseResult
import com.sahalnazar.paging3_retrofit_roomdb.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _movieDetails: MutableLiveData<BaseResult<MovieDetailResponse?>> = MutableLiveData()
    val movieDetails: LiveData<BaseResult<MovieDetailResponse?>> = _movieDetails

    fun fetchMovieDetails(movieId: String) {
        _movieDetails.value = BaseResult.loading()
        viewModelScope.launch {
            when (val result = repository.fetMovieDetail(movieId)) {
                is ResultWrapper.Success -> {
                    _movieDetails.value = BaseResult.success(result.data, result.code)
                }
                is ResultWrapper.Failure -> {
                    _movieDetails.value = BaseResult.success(null, result.code)
                }
            }
        }
    }

}