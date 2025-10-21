package com.example.ozinshe20.data

import com.example.ozinshe20.data.model.LoginAndRegisterRequest
import com.example.ozinshe20.data.model.LoginResponse
import com.example.ozinshe20.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginAndRegisterRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun registerUser(@Body registerRequest: LoginAndRegisterRequest): RegisterResponse
}