package com.example.marsrobots.application.injection

import androidx.lifecycle.ViewModel
import com.example.marsrobots.application.arch.ViewModelKey
import com.example.marsrobots.application.scope.AppScope
import com.example.marsrobots.network.HomeApi
import com.example.marsrobots.repositories.home.HomeRepository
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
    fun providesHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepository(homeApi)
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun providesHomeViewModel(homeRepository: HomeRepository): ViewModel {
        return HomeViewModel(homeRepository)
    }


}