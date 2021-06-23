package com.example.marsrobots.application.injection

import androidx.lifecycle.ViewModel
import com.example.marsrobots.application.arch.ViewModelKey
import com.example.marsrobots.application.scope.AppScope
import com.example.marsrobots.network.HomeApi
import com.example.marsrobots.repositories.home.HomeRepository
import com.example.marsrobots.room.ImageDao
import com.example.marsrobots.room.MarsRobotsDb
import com.example.marsrobots.viewmodels.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
class HomeModule {
    @AppScope
    @Provides
    fun providesHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @AppScope
    @Provides
    fun providesImageDao(database: MarsRobotsDb): ImageDao {
        return database.imageDao()
    }

    @AppScope
    @Provides
    fun providesHomeRepository(homeApi: HomeApi, imageDao: ImageDao): HomeRepository {
        return HomeRepository(homeApi,imageDao)
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun providesHomeViewModel(homeRepository: HomeRepository): ViewModel {
        return HomeViewModel(homeRepository)
    }


}