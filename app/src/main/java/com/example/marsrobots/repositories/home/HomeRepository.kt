package com.example.marsrobots.repositories.home

import com.example.marsrobots.network.HomeApi
import com.example.marsrobots.network.response.ImageFetchApiResponse
import com.example.marsrobots.utils.applyIoSchedulers
import io.reactivex.rxjava3.core.Single

class HomeRepository(private val homeApi: HomeApi) {

    fun getImageInfo(searchType: String, mediaType: String): Single<ImageFetchApiResponse> {
        return homeApi.getImageInfo(searchType, mediaType).applyIoSchedulers()
    }
}