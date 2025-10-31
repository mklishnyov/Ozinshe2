package com.example.ozinshe20.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe20.data.ApiService
import com.example.ozinshe20.data.ServiceBuilder
import com.example.ozinshe20.data.model.MainMoviesResponse
import com.example.ozinshe20.data.model.MoviesByCategoryMainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    private val apiService = ServiceBuilder.buildService(ApiService::class.java)

    private var _mainMoviesResponse: MutableLiveData<MainMoviesResponse> = MutableLiveData()
    val mainMoviesResponse: LiveData<MainMoviesResponse> = _mainMoviesResponse
    private var _moviesByCategoryMainModel: MutableLiveData<MoviesByCategoryMainModel> = MutableLiveData()
    val moviesByCategoryMainModel: LiveData<MoviesByCategoryMainModel> = _moviesByCategoryMainModel

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getMainMovies(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { apiService.getMainMovies("Bearer $token") }
                .onSuccess {
                    _mainMoviesResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun getMoviesByCategoryMain(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { apiService.getMoviesByCategoryMain("Bearer $token") }
                .onSuccess {
                    _moviesByCategoryMainModel.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}