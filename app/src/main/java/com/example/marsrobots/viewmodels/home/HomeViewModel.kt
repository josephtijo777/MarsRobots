package com.example.marsrobots.viewmodels.home

import androidx.lifecycle.*
import com.example.marsrobots.common.ViewState
import com.example.marsrobots.network.response.ImageFetchApiResponse
import com.example.marsrobots.network.response.Item
import com.example.marsrobots.repositories.home.HomeRepository
import com.example.marsrobots.room.ImageEntity
import com.example.marsrobots.utils.Helper
import com.example.marsrobots.utils.toImageEntityRecord
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    var compositeDisposable = CompositeDisposable()

    val viewState = MutableLiveData<ViewState>()

    var imageFetchApiResponse = MutableLiveData<ImageFetchApiResponse>()
    var imageListLiveData = MutableLiveData<List<ImageEntity>>()
    var errorMessage: String? = null

    fun getOfflineData() {
        imageListLiveData =
            homeRepository.getAllImages().asLiveData() as MutableLiveData<List<ImageEntity>>
    }

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
                    mapData(it.collection.items)
                    viewState.value = ViewState.LOADED
                }
            )
    }

    private fun mapData(items: List<Item>) {
        var list = mutableListOf<ImageEntity>()
        for (x in items.indices) {
            var imageEntity = items[x].toImageEntityRecord()
            list.add(imageEntity)
            insert(imageEntity)
        }
        imageListLiveData.value = list
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(imageEntity: ImageEntity) = viewModelScope.launch {
        homeRepository.insert(imageEntity)
    }
}