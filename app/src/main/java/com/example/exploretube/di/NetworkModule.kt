package com.example.exploretube.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.exploretube.constants.Constants
import com.example.exploretube.interceptor.AuthInterceptor
import com.example.exploretube.model.db.MediaDataBase
import com.example.exploretube.model.db.getDataBase
import com.example.exploretube.repository.MediaRepository
import com.example.exploretube.service.MediaService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @Singleton
    fun providesAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun providesRetrofitInstance(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun providesMediaService(retrofit: Retrofit): MediaService =
        retrofit.create(MediaService::class.java)

    @Provides
    @Singleton
    fun providesMediaRepository(mediaService: MediaService, mediaDataBase: MediaDataBase) =
        MediaRepository(mediaService, mediaDataBase)

    @Provides
    @Singleton
    fun providesMediaDatabase(@ApplicationContext context: Context) = getDataBase(context)

}