package com.kusu.myaccounts.app

import dagger.Component
import javax.inject.Singleton

/**
 * Created by innofied on 17/7/18.
 */
@Singleton
@Component(modules = [(MyAppModule::class)])
interface MyAppComponent {
    fun provideApp(): MyAppDatabase
}