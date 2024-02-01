package com.example.exploretube.service

import com.example.exploretube.model.remote.PhotoResponse
import com.example.exploretube.model.remote.VideoResponse
import retrofit2.Response
import retrofit2.http.GET

interface MediaService {

    @GET("v1/curated")
    suspend fun getPhotos(): Response<PhotoResponse>

    @GET("videos/popular")
    suspend fun getVideos(): Response<VideoResponse>

}