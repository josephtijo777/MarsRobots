package com.example.marsrobots.application.injection

import com.example.marsrobots.application.scope.ActivityScope
import com.example.marsrobots.ui.home.HomeActivity
import com.example.marsrobots.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivityInjector(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun homeActivityInjector(): HomeActivity
}