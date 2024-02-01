package com.example.exploretube.repository

import com.example.exploretube.base.BaseRepository
import com.example.exploretube.base.BaseResponse
import com.example.exploretube.model.db.MediaDataBase
import com.example.exploretube.model.db.getDataBase
import com.example.exploretube.model.remote.PhotoResponse
import com.example.exploretube.service.MediaService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val mediaService: MediaService, private val mediaDataBase: MediaDataBase
) : BaseRepository() {

    suspend fun getPhotos() = flow {
        emit(BaseResponse.Loading())
        val result = handleResponse(mediaService.getPhotos())
        emit(result)
    }.flowOn(Dispatchers.IO)

    suspend fun getVideos() = flow {
        emit(BaseResponse.Loading())
        val result = handleResponse((mediaService.getVideos()))

        emit(result)
    }.flowOn(Dispatchers.IO)

    fun getPhotosFromDB() = mediaDataBase.mediaDatabaseDao.getPhotosFromRoom()

    fun getVideosFromDB() = mediaDataBase.mediaDatabaseDao.getVideosFromRoom()
}