package com.example.marsrobots.viewmodels.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsrobots.common.ViewState
import com.example.marsrobots.network.response.ImageFetchApiResponse
import com.example.marsrobots.repositories.home.HomeRepository
import com.example.marsrobots.utils.Helper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    var compositeDisposable = CompositeDisposable()

    val viewState = MutableLiveData<ViewState>()

    var imageFetchApiResponse = MutableLiveData<ImageFetchApiResponse>()
    var errorMessage: String? = null

    fun getImageInfo(searchType: String, mediaType: String) {
        compositeDisposable += homeRepository.getImageInfo(searchType, mediaType)
            .doOnSubscribe {
                viewState.value = ViewState.LOADING
            }
            .subscribeBy(
                onError = {
                    errorMessage = Helper.getErrorMessage(it)
                    viewState.value = ViewState.ERROR
                },
                onSuccess = {
                    imageFetchApiResponse.value = it
                    viewState.value = ViewState.LOADED
                }
            )
    }
}