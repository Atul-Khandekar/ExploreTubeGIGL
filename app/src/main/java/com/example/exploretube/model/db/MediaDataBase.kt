package com.example.exploretube.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exploretube.model.dao.MediaDao
import com.example.exploretube.model.local.Photos
import com.example.exploretube.model.local.Videos

@Database(entities = [Photos::class, Videos::class], version = 1, exportSchema = false)
abstract class MediaDataBase : RoomDatabase() {
    abstract val mediaDatabaseDao: MediaDao
}

private lateinit var INSTANCE: MediaDataBase

fun getDataBase(context: Context): MediaDataBase {

    if (!::INSTANCE.isInitialized) {
        INSTANCE = Room.databaseBuilder(context, MediaDataBase::class.java, "media_database")
            .allowMainThreadQueries().build()
    }
    return INSTANCE
}