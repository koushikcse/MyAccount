package com.kusu.myaccounts.fearture.viewAccount.di

import com.example.myapplication.feature.login.data.local.AccountDao
import com.kusu.myaccounts.app.MyAppDatabase
import com.kusu.myaccounts.fearture.viewAccount.data.ViewAccountRepo
import com.kusu.myaccounts.fearture.viewAccount.data.ViewAccountRepoImpl
import dagger.Module
import dagger.Provides

/**
 * Created by innofied on 24/7/18.
 */

@Module
class ViewAccountModule {

    @ViewAccountScope
    @Provides
    internal fun provideViewAccountRepo(appDatabase: MyAppDatabase): ViewAccountRepo {
        return ViewAccountRepoImpl(appDatabase)
    }

    @ViewAccountScope
    @Provides
    internal fun provideAccountDao(db: MyAppDatabase): AccountDao {
        return db.accountTableDao()
    }
}