package com.kusu.myaccounts.fearture.addAccount.data

import com.example.myapplication.feature.login.data.local.AccountTable
import com.kusu.myaccounts.app.MyAppDatabase
import com.kusu.myaccounts.base.model.Account
import io.reactivex.Flowable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by innofied on 23/7/18.
 */

@Singleton
class AddAccountRepoImpl @Inject constructor(private val appDatabase: MyAppDatabase) : AddAccountRepo {

    override fun addAccount(acc: Account): Flowable<Account> {
        val accountTable = AccountTable()
        accountTable.accName = acc.name
        accountTable.accKey1 = acc.key1
        accountTable.accKey2 = acc.key2
        accountTable.accKey3 = acc.key3
        accountTable.accValue1 = acc.value1
        accountTable.accValue2 = acc.value2
        accountTable.accValue3 = acc.value3
        appDatabase.accountTableDao().insertAccounts(accountTable)

        val entries = appDatabase.accountTableDao().getAccountsFromaccName(acc.name)
//        return entries.flatMap({ t: AccountTable -> convrtAccount(t) })
        return entries.flatMap {convrtAccount(accountTable)}
    }

    private fun convrtAccount(t: AccountTable): Flowable<Account>? {
        val acc = Account()
        acc.id = t.accId!!
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