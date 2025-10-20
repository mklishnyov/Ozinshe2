package com.example.ozinshe20.data

import com.example.ozinshe20.data.model.LoginRequest
import com.example.ozinshe20.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse
}