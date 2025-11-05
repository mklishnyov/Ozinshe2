package com.example.ozinshe20.data.model


import com.google.gson.annotations.SerializedName

data class MovieByIdResponse(
    @SerializedName("categories")
    val categories: List<CategoryXX>,
    @SerializedName("categoryAges")
    val categoryAges: List<CategoryAgeXX>,
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("genres")
    val genres: List<GenreXX>,
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
    val poster: PosterXX,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("screenshots")
    val screenshots: List<ScreenshotXX>,
    @SerializedName("seasonCount")
    val seasonCount: Int,
    @SerializedName("seriesCount")
    val seriesCount: Int,
    @SerializedName("timing")
    val timing: Int,
    @SerializedName("trend")
    val trend: Boolean,
    @SerializedName("video")
    val video: VideoXX?,
    @SerializedName("watchCount")
    val watchCount: Int,
    @SerializedName("year")
    val year: Int
)