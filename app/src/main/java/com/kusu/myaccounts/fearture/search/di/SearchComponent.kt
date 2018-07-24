package com.kusu.myaccounts.fearture.search.di

import com.kusu.myaccounts.app.MyAppComponent
import com.kusu.myaccounts.fearture.search.presentation.SearchViewModel
import dagger.Component

/**
 * Created by innofied on 24/7/18.
 */
@SearchScope
@Component(modules = [(SearchModule::class)], dependencies = [(MyAppComponent::class)])
interface SearchComponent {
    fun inject(searchViewModel: SearchViewModel)
}