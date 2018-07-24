package com.kusu.myaccounts.fearture.viewAccount.data

import com.example.myapplication.feature.login.data.local.AccountTable
import com.kusu.myaccounts.app.MyAppDatabase
import com.kusu.myaccounts.base.model.Account
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by innofied on 24/7/18.
 */
@Singleton
class ViewAccountRepoImpl @Inject constructor(private val appDatabase: MyAppDatabase) : ViewAccountRepo {

    override fun getAccount(accId: Int): Flowable<Account> {
        val entries = appDatabase.accountTableDao().getAccountFromaccId(accId)
        return entries.flatMap({ t: AccountTable -> convrtAccount(t) })
//        return entries.flatMap {convrtAccount(accountTable)}
    }

    private fun convrtAccount(t: AccountTable): Flowable<Account>? {
        val acc = Account()
        acc.id = t.accId
        acc.name = t.accName
        acc.key1 = t.accKey1
        acc.key2 = t.accKey2
        acc.key3 = t.accKey3
        acc.value1 = t.accValue1
        acc.value2 = t.accValue2
        acc.value3 = t.accValue3

        return Flowable.just(acc)


    }
}