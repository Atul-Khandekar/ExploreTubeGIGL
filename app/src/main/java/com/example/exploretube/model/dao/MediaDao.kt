package com.example.exploretube.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exploretube.model.local.Photos
import com.example.exploretube.model.local.Videos

@Dao
interface MediaDao {

    @Query("select * from photo_table")
    fun getPhotosFromRoom(): LiveData<List<Photos>>

    @Query("select * from video_table")
    fun getVideosFromRoom(): LiveData<List<Videos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotosToRoom(photos: List<Photos>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideosToRoom(videos: List<Videos>)
}