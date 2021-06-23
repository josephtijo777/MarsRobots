package com.example.marsrobots.application.injection

import androidx.lifecycle.ViewModelProvider
import com.example.marsrobots.application.arch.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
