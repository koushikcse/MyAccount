package com.kusu.myaccounts.app

import android.app.Application
import com.kusu.myaccounts.fearture.addAccount.di.AddAccountComponent
import com.kusu.myaccounts.fearture.addAccount.di.AddAccountModule
import com.kusu.myaccounts.fearture.addAccount.di.DaggerAddAccountComponent
import com.kusu.myaccounts.fearture.search.di.DaggerSearchComponent
import com.kusu.myaccounts.fearture.search.di.SearchComponent
import com.kusu.myaccounts.fearture.search.di.SearchModule
import com.kusu.myaccounts.fearture.viewAccount.di.DaggerViewAccountComponent
import com.kusu.myaccounts.fearture.viewAccount.di.ViewAccountComponent
import com.kusu.myaccounts.fearture.viewAccount.di.ViewAccountModule

/**
 * Created by innofied on 17/7/18.
 */
class MyApp : Application() {
    lateinit var appComponent: MyAppComponent
    var addAccountComponent: AddAccountComponent? = null
    var viewAccountComponent: ViewAccountComponent? = null
    var searchComponent: SearchComponent? = null
    override fun onCreate() {
        super.onCreate()
        appComponent=buildAppComponent()

    }

    private fun buildAppComponent(): MyAppComponent {
        return DaggerMyAppComponent.builder()
                .myAppModule(MyAppModule(this))
                .build()

    }

    fun buildAddAccountComponent(): AddAccountComponent {
        addAccountComponent = DaggerAddAccountComponent.builder()
                .myAppComponent(appComponent)
                .addAccountModule(AddAccountModule())
                .build()
        return addAccountComponent!!
    }

    fun buildViewAccountComponent(): ViewAccountComponent {
        viewAccountComponent = DaggerViewAccountComponent.builder()
                .myAppComponent(appComponent)
                .viewAccountModule(ViewAccountModule())
                .build()
        return viewAccountComponent!!
    }
      fun buildSearchComponent(): SearchComponent {
          searchComponent = DaggerSearchComponent.builder()
                .myAppComponent(appComponent)
                .searchModule(SearchModule())
                .build()
        return searchComponent!!
    }

}