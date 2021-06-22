package com.example.marsrobots.application;

import com.example.marsrobots.application.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MarsRobotsApplication : DaggerApplication(){
  override fun applicationInjector(): AndroidInjector<MarsRobotsApplication> =
   DaggerApplicationComponent.factory().create(this)
}
