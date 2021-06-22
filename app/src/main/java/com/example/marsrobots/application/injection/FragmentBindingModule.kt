package com.example.marsrobots.application.injection


import com.example.marsrobots.application.scope.FragmentScope
import com.example.marsrobots.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun homeFragmentInjector(): HomeFragment
}