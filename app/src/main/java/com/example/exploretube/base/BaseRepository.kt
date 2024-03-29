package com.example.exploretube.base

import com.example.exploretube.utils.ApiError
import com.google.gson.Gson
import retrofit2.Response

open class BaseRepository {

    fun <T> handleResponse(response: Response<T>): BaseResponse<T> {
        return try {
            if (response.isSuccessful) {
                BaseResponse.Success(response.body())
            } else {
                if (response.code() in 400..499) {
                    response.errorBody().let {
                        val errorResponse =
                            Gson().fromJson(response.errorBody()?.string(), ApiError::class.java)
                        BaseResponse.Error(errorResponse.message ?: "Something Went Wrong")
                    }
                } else {
                    BaseResponse.Error(response.message())
                }
            }

        } catch (e: Error) {
            BaseResponse.Error(e.message ?: "Something went wrong")
        }
    }
}