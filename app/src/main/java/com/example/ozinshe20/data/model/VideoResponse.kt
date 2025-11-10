package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("videos")
    val videos: List<VideoXXX>
)