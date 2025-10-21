package com.example.ozinshe20.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe20.data.ApiService
import com.example.ozinshe20.data.ServiceBuilder
import com.example.ozinshe20.data.model.LoginAndRegisterRequest
import com.example.ozinshe20.data.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(): ViewModel() {

    private var _registerResponse: MutableLiveData<RegisterResponse> = MutableLiveData()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)

            runCatching { response.registerUser(LoginAndRegisterRequest(email, password)) }
                .onSuccess {
                    _registerResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}