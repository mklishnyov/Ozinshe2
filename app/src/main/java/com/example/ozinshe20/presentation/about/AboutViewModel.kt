package com.example.ozinshe20.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe20.data.ApiService
import com.example.ozinshe20.data.ServiceBuilder
import com.example.ozinshe20.data.model.MainMoviesResponseItem
import com.example.ozinshe20.data.model.MovieByIdResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel: ViewModel() {
    private val apiService = ServiceBuilder.buildService(ApiService::class.java)

    private var _moviesByIDResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIDResponse: LiveData<MovieByIdResponse> = _moviesByIDResponse
    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getMoviesById(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { apiService.getMoviesById("Bearer $token", movieId) }
                .onSuccess {
                    _moviesByIDResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}