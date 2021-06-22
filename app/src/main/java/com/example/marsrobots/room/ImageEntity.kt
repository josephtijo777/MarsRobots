package com.example.marsrobots.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageEntity(
    @PrimaryKey @ColumnInfo(name = "url") val url: String,
    val description: String,
    val date: String
)
