package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class CategoryAgeX(
    @SerializedName("fileId")
    val fileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("movieCount")
    val movieCount: Int,
    @SerializedName("name")
    val name: String
)