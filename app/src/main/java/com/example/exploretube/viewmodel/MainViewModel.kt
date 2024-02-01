package com.example.exploretube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploretube.base.BaseResponse
import com.example.exploretube.model.local.Media
import com.example.exploretube.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mediaRepository: MediaRepository): ViewModel() {

    private val _photos = MutableStateFlow<List<Media>>(emptyList())
    val photos get() = _photos.asStateFlow()

    private val _videos = MutableStateFlow<List<Media>>(emptyList())
    val videos get() = _videos.asStateFlow()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> get() = _error

    init {
        getPhotos()
        getVideos()
    }

    private fun getPhotos() {

       viewModelScope.launch {
           mediaRepository.getPhotos().collectLatest {response ->
               when(response) {
                   is BaseResponse.Loading -> {
                        _isLoading.value = true
                   }

                   is BaseResponse.Success -> {
                       response.data.let {photos ->
                           val list = photos?.photos?.map {
                               Media(it?.id.toString() , it?.src?.medium?:"Unknown")
                           }
                           list?.let {
                               _isLoading.value  = false
                               _photos.emit(it)
                           }

                       }
                   }

                   is BaseResponse.Error -> {
                       _isLoading.value  = false
                       _error.value = response.msg
                   }
               }
           }
       }
    }

    private fun getVideos() {
        viewModelScope.launch {
            mediaRepository.getVideos().collectLatest {response ->
                when(response) {
                    is BaseResponse.Loading -> {
                        _isLoading.value = true
                    }

                    is BaseResponse.Success -> {
                        response.data.let {videos ->
                            val list = videos?.videos?.map {
                                Media(it?.id.toString() , it?.image.toString())
                            }
                            list?.let {
                                _isLoading.value  = false
                                _videos.emit(it)
                            }

                        }
                    }

                    is BaseResponse.Error -> {
                        _isLoading.value  = false
                        _error.value = response.msg
                    }
                }
            }
        }
    }
}