package com.example.exploretube.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class Videos(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    @ColumnInfo(name = "image_url")
    val image: String
)

data class Photos(
    val id: String,
    val image: String
)