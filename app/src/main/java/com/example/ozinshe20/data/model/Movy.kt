package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class Movy(
    @SerializedName("categories")
    val categories: List<CategoryX>,
    @SerializedName("categoryAges")
    val categoryAges: List<CategoryAgeX>,
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("genres")
    val genres: List<GenreX>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("keyWords")
    val keyWords: String,
    @SerializedName("lastModifiedDate")
    val lastModifiedDate: String,
    @SerializedName("movieType")
    val movieType: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster")
    val poster: PosterX,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("screenshots")
    val screenshots: List<ScreenshotX>,
    @SerializedName("seasonCount")
    val seasonCount: Int,
    @SerializedName("seriesCount")
    val seriesCount: Int,
    @SerializedName("timing")
    val timing: Int,
    @SerializedName("trend")
    val trend: Boolean,
    @SerializedName("video")
    val video: VideoX,
    @SerializedName("watchCount")
    val watchCount: Int,
    @SerializedName("year")
    val year: Int
)