package com.example.ozinshe20.data

import com.example.ozinshe20.data.model.LoginAndRegisterRequest
import com.example.ozinshe20.data.model.LoginResponse
import com.example.ozinshe20.data.model.MainMoviesResponse
import com.example.ozinshe20.data.model.MainMoviesResponseItem
import com.example.ozinshe20.data.model.MovieByIdResponse
import com.example.ozinshe20.data.model.MoviesByCategoryMainModel
import com.example.ozinshe20.data.model.RegisterResponse
import com.example.ozinshe20.data.model.VideoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/auth/V1/signin")
    suspend fun loginUser(@Body loginRequest: LoginAndRegisterRequest): LoginResponse

    @POST("/auth/V1/signup")
    suspend fun registerUser(@Body registerRequest: LoginAndRegisterRequest): RegisterResponse

    @GET("/core/V1/movies_main")
    suspend fun getMainMovies(@Header("Authorization") token: String): MainMoviesResponse

    @GET("/core/V1/movies/main")
    suspend fun getMoviesByCategoryMain(@Header("Authorization") token: String): MoviesByCategoryMainModel

    @GET("/core/V1/movies/{id}")
    suspend fun getMoviesById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
        ): MovieByIdResponse

    @GET("core/V1/seasons/{id}")
    suspend fun getSeriesById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): List<VideoResponse>
}