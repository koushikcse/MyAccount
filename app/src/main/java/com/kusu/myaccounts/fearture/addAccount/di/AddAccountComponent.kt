package com.kusu.myaccounts.fearture.addAccount.di

import com.kusu.myaccounts.app.MyAppComponent
import com.kusu.myaccounts.fearture.addAccount.presentation.AddAccountViewModel
import dagger.Component

/**
 * Created by innofied on 17/7/18.
 */

@AddAccountScope
@Component(modules = [(AddAccountModule::class)], dependencies = [(MyAppComponent::class)])
interface AddAccountComponent {
    fun inject(addAccountViewModel: AddAccountViewModel)
}