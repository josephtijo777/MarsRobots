package com.example.marsrobots.application;

import com.example.marsrobots.application.injection.DaggerApplicationComponent
import com.example.marsrobots.repositories.home.HomeRepository
import com.example.marsrobots.room.MarsRobotsDb
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MarsRobotsApplication : DaggerApplication(){

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MarsRobotsDb.getDatabase(this, applicationScope) }

  override fun applicationInjector(): AndroidInjector<MarsRobotsApplication> =
   DaggerApplicationComponent.factory().create(this)
}
