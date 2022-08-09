package com.sahalnazar.paging3_retrofit_roomdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahalnazar.paging3_retrofit_roomdb.data.model.MovieDetailResponse
import com.sahalnazar.paging3_retrofit_roomdb.data.remote.AppRepository
import com.sahalnazar.paging3_retrofit_roomdb.util.BaseResult
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
        viewModelScope.launch {
            repository.fetMovieDetail(movieId).collect { response ->
                when (response.status) {
                    BaseResult.Status.SUCCESS -> {
                        _movieDetails.value = BaseResult.success(response.data, response.code)
                    }
                    BaseResult.Status.ERROR -> {
                        _movieDetails.value = BaseResult.error(response.message ?: "Something went wrong", null, null)
                    }
                    BaseResult.Status.LOADING -> {
                        _movieDetails.value = BaseResult.loading()
                    }
                }
            }
        }
    }

}