package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class VideoXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("seasonId")
    val seasonId: Any
)