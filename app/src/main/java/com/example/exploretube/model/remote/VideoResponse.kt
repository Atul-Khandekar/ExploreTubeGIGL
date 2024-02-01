package com.example.exploretube.model.remote


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("videos")
    val videos: List<Video?>?
)

data class VideoPicture(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nr")
    val nr: Int?,
    @SerializedName("picture")
    val picture: String?
)

data class Video(
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("video_files")
    val videoFiles: List<VideoFile?>?,
    @SerializedName("video_pictures")
    val videoPictures: List<VideoPicture?>?,
    @SerializedName("width")
    val width: Int?
)

data class User(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)

data class VideoFile(
    @SerializedName("file_type")
    val fileType: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("quality")
    val quality: String?,
    @SerializedName("width")
    val width: Int?
)