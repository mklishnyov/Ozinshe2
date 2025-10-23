package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class MainMoviesResponseItem(
    @SerializedName("fileId")
    val fileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("movie")
    val movie: Movie,
    @SerializedName("sortOrder")
    val sortOrder: Int
)