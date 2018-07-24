package com.kusu.myaccounts.fearture.viewAccount.di

import com.kusu.myaccounts.app.MyAppComponent
import com.kusu.myaccounts.fearture.viewAccount.presenter.ViewAccountViewModel
import dagger.Component

/**
 * Created by innofied on 24/7/18.
 */

@ViewAccountScope
@Component(modules = [(ViewAccountModule::class)], dependencies = [(MyAppComponent::class)])
interface ViewAccountComponent {
    fun inject(viewAccountViewModel: ViewAccountViewModel)
}