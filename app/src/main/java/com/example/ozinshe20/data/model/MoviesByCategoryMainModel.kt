package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class MoviesByCategoryMainModel(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("categoryName")
    val categoryName: String,
    @SerializedName("movies")
    val movies: List<Movy>
)