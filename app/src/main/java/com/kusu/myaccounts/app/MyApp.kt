package com.kusu.myaccounts.app

import android.app.Application

/**
 * Created by innofied on 17/7/18.
 */
class MyApp : Application() {
    lateinit var appComponent: MyAppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent=buildAppComponent()

    }

    private fun buildAppComponent(): MyAppComponent {
        return DaggerMyAppComponent.builder()
                .myAppModule(MyAppModule(this))
                .build()

    }
}