package com.example.ozinshe20.data.model

import com.google.gson.annotations.SerializedName

data class LoginAndRegisterRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)