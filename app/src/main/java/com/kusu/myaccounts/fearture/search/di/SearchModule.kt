package com.kusu.myaccounts.fearture.search.di

import com.example.myapplication.feature.login.data.local.AccountDao
import com.kusu.myaccounts.app.MyAppDatabase
import com.kusu.myaccounts.fearture.search.data.SearchRepo
import com.kusu.myaccounts.fearture.search.data.SearchRepoImpl
import dagger.Module
import dagger.Provides

/**
 * Created by innofied on 24/7/18.
 */
@Module
class SearchModule {

    @SearchScope
    @Provides
    internal fun provideSearchRepo(appDatabase: MyAppDatabase): SearchRepo {
        return SearchRepoImpl(appDatabase)
    }

    @SearchScope
    @Provides
    internal fun provideAccountDao(db: MyAppDatabase): AccountDao {
        return db.accountTableDao()
    }
}