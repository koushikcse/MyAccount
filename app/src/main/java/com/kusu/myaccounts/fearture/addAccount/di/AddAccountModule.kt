package com.kusu.myaccounts.fearture.addAccount.di

import com.example.myapplication.feature.login.data.local.AccountDao
import com.kusu.myaccounts.app.MyAppDatabase
import com.kusu.myaccounts.fearture.addAccount.data.AddAccountRepo
import com.kusu.myaccounts.fearture.addAccount.data.AddAccountRepoImpl
import dagger.Module
import dagger.Provides

/**
 * Created by innofied on 17/7/18.
 */

@Module
class AddAccountModule {

    @AddAccountScope
    @Provides
    internal fun provideAddAccountRepo(appDatabase: MyAppDatabase): AddAccountRepo {
        return AddAccountRepoImpl(appDatabase)
    }

    @AddAccountScope
    @Provides
    internal fun provideAccountDao(db: MyAppDatabase): AccountDao {
        return db.accountTableDao()

    }
}