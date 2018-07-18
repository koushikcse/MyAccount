package com.kusu.myaccounts.app

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by innofied on 17/7/18.
 */
@Module
class MyAppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApp(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideDb(): MyAppDatabase {
        return Room.databaseBuilder(application, MyAppDatabase::class.java, "cleanapp.db").build()
    }

}