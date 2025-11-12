package com.example.ozinshe20.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe20.data.ApiService
import com.example.ozinshe20.data.ServiceBuilder
import com.example.ozinshe20.data.model.MainMoviesResponseItem
import com.example.ozinshe20.data.model.MovieByIdResponse
import com.example.ozinshe20.data.model.MovieIdResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutViewModel: ViewModel() {
    private val apiService = ServiceBuilder.buildService(ApiService::class.java)

    private var _favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    val favoriteState: LiveData<Boolean> = _favoriteState

    private var _moviesByIDResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIDResponse: LiveData<MovieByIdResponse> = _moviesByIDResponse

    private var _moviesAddResponse: MutableLiveData<MovieIdResponse> = MutableLiveData()
    val moviesAddResponse: LiveData<MovieIdResponse> = _moviesAddResponse

    private var _moviesDeleteResponse: MutableLiveData<String> = MutableLiveData()
    val moviesDeleteResponse: LiveData<String> = _moviesDeleteResponse

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

    fun addFavorite(token: String, movieId: MovieIdResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { apiService.addFavorite("Bearer $token", movieId) }
                .onSuccess {
                    _favoriteState.postValue(true)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun deleteFavorite(token: String, movieId: MovieIdResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { apiService.deleteFavorite("Bearer $token", movieId) }
                .onSuccess {
                    _favoriteState.postValue(false)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}