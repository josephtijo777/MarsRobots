package com.example.marsrobots.application.injection


import com.example.marsrobots.application.MarsRobotsApplication
import com.example.marsrobots.application.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @AppScope
    @Provides
    fun context(app: MarsRobotsApplication) = app.applicationContext
}