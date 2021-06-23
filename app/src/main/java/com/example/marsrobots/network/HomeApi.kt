package com.example.marsrobots.network

import com.example.marsrobots.constants.FETCH_IMAGE_API
import com.example.marsrobots.constants.MEDIA_TYPE
import com.example.marsrobots.constants.SEARCH_TYPE
import com.example.marsrobots.network.response.ImageFetchApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET(FETCH_IMAGE_API)
    fun getImageInfo(@Query(SEARCH_TYPE) searchType: String, @Query(MEDIA_TYPE) mediaType: String)
            : Single<ImageFetchApiResponse>
}