package com.example.marsrobots.application.injection


import android.content.Context
import com.example.marsrobots.application.MarsRobotsApplication
import com.example.marsrobots.application.scope.AppScope
import com.example.marsrobots.room.MarsRobotsDb
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class ApplicationModule {
    @AppScope
    @Provides
    fun context(app: MarsRobotsApplication): Context {
        return app.applicationContext
    }

    @AppScope
    @Provides
    fun applicationScope(app: MarsRobotsApplication): CoroutineScope {
        return app.applicationScope
    }

    @AppScope
    @Provides
    fun providesDatabase(context: Context, coroutineScope: CoroutineScope): MarsRobotsDb {
        return MarsRobotsDb.getDatabase(context, coroutineScope)
    }
}