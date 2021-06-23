package com.example.ticketapp.application.injection

import com.example.marsrobots.application.scope.AppScope
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Module
class RxModule {
    @AppScope
    @Provides
    fun providesCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}