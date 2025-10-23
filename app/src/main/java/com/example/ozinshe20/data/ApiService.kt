package com.example.ozinshe20.data

import com.example.ozinshe20.data.model.LoginAndRegisterRequest
import com.example.ozinshe20.data.model.LoginResponse
import com.example.ozinshe20.data.model.MainMoviesResponse
import com.example.ozinshe20.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String): MainMoviesResponse

    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginAndRegisterRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun registerUser(@Body registerRequest: LoginAndRegisterRequest): RegisterResponse
}