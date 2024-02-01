package com.example.exploretube.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploretube.base.BaseResponse
import com.example.exploretube.constants.Constants
import com.example.exploretube.model.db.MediaDataBase
import com.example.exploretube.model.local.Videos
import com.example.exploretube.model.local.Photos
import com.example.exploretube.repository.MediaRepository
import com.example.exploretube.utils.Connectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
    private val mediaDataBase: MediaDataBase,
    @ApplicationContext context: Context
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> get() = _error

    init {
        if (Connectivity.isNetworkAvailable(context)) {
            getPhotos()
            getVideos()
        } else {
            _error.value = Constants.INTERNET_ERROR
        }
        getPhotosFromDB()
        getVideosFromDB()

    }

    private fun getPhotos() {

        viewModelScope.launch {
            mediaRepository.getPhotos().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.value = true
                    }

                    is BaseResponse.Success -> {
                        response.data.let { photos ->
                            val list = photos?.photos?.map {
                                Photos(it?.id ?: 0, it?.src?.medium ?: "Unknown")
                            }
                            list?.let {
                                _isLoading.value = false
                                mediaDataBase.mediaDatabaseDao.insertPhotosToRoom(it)
                            }

                        }
                    }

                    is BaseResponse.Error -> {
                        _isLoading.value = false
                        _error.value = response.msg

                    }
                }
            }
        }
    }

    private fun getVideos() {
        viewModelScope.launch {
            mediaRepository.getVideos().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.value = true
                    }

                    is BaseResponse.Success -> {
                        response.data.let { videos ->
                            val list = videos?.videos?.map {
                                Videos(it?.id ?: 0, it?.image.toString())
                            }
                            list?.let {
                                _isLoading.value = false
                                mediaDataBase.mediaDatabaseDao.insertVideosToRoom(it)
                            }

                        }
                    }

                    is BaseResponse.Error -> {
                        _isLoading.value = false
                        _error.value = response.msg
                    }
                }
            }
        }
    }

    fun getPhotosFromDB() = mediaRepository.getPhotosFromDB()

    fun getVideosFromDB() = mediaRepository.getVideosFromDB()
}