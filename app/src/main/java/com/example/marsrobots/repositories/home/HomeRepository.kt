package com.example.marsrobots.repositories.home

import androidx.annotation.WorkerThread
import com.example.marsrobots.network.HomeApi
import com.example.marsrobots.network.response.ImageFetchApiResponse
import com.example.marsrobots.room.ImageDao
import com.example.marsrobots.room.ImageEntity
import com.example.marsrobots.utils.applyIoSchedulers
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class HomeRepository(private val homeApi: HomeApi, private val imageDao: ImageDao) {

    fun getImageInfo(searchType: String, mediaType: String): Single<ImageFetchApiResponse> {
        return homeApi.getImageInfo(searchType, mediaType).applyIoSchedulers()
    }

    fun getAllImages(): Flow<List<ImageEntity>> {
        return imageDao.getAllImages()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(imageEntity: ImageEntity) {
        imageDao.insert(imageEntity)
    }
}