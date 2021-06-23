package com.example.marsrobots.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM image_table")
    fun getAllImages(): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(imageEntity: ImageEntity)

    @Query("DELETE FROM image_table")
    suspend fun deleteAll()

}