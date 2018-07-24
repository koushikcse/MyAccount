package com.kusu.myaccounts.fearture.search.data

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
class SearchRepoImpl @Inject constructor(private val appDatabase: MyAppDatabase) : SearchRepo {
    override fun getAllAccounts(): Flowable<List<Account>> {
        val entries = appDatabase.accountTableDao().all
        return entries.flatMap { t -> convrtAccount(t) }
    }

    private fun convrtAccount(t: List<AccountTable>): Flowable<List<Account>>? {
        val accList = ArrayList<Account>()
        for (acctable in t) {
            val acc = Account()
            acc.id = acctable.accId
            acc.name = acctable.accName
            acc.key1 = acctable.accKey1
            acc.key2 = acctable.accKey2
            acc.key3 = acctable.accKey3
            acc.value1 = acctable.accValue1
            acc.value2 = acctable.accValue2
            acc.value3 = acctable.accValue3
            accList.add(acc)
        }

        return Flowable.just(accList)
    }
}