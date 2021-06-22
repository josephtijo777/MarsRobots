package com.example.marsrobots.application.injection

import com.example.marsrobots.application.MarsRobotsApplication
import com.example.marsrobots.application.scope.AppScope
import com.example.ticketapp.application.injection.RxModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ApiModule::class,
        RxModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        HomeModule::class,
        ViewModelModule::class]
)
internal interface ApplicationComponent : AndroidInjector<MarsRobotsApplication> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<MarsRobotsApplication>
}