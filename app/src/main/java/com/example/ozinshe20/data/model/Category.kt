package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("fileId")
    val fileId: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: Any,
    @SerializedName("movieCount")
    val movieCount: Any,
    @SerializedName("name")
    val name: String
)