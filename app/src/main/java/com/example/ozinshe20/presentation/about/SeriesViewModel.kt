package com.example.ozinshe20.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe20.data.ApiService
import com.example.ozinshe20.data.ServiceBuilder
import com.example.ozinshe20.data.model.MovieByIdResponse
import com.example.ozinshe20.data.model.VideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    private val apiService = ServiceBuilder.buildService(ApiService::class.java)

    private var _videosResponse: MutableLiveData<List<VideoResponse>> = MutableLiveData()
    val videosResponse: LiveData<List<VideoResponse>> = _videosResponse
    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getSeriesById(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { apiService.getSeriesById("Bearer $token", movieId) }
                .onSuccess {
                    _videosResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}