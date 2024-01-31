package com.example.exploretube.interceptor

import com.example.exploretube.constants.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().addHeader("Authorization", Constants.API_KEY).build()
        return chain.proceed(request)
    }
}