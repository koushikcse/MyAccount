package com.kusu.myaccounts.fearture.search.data

import com.kusu.myaccounts.base.model.Account
import io.reactivex.Flowable

/**
 * Created by innofied on 24/7/18.
 */
interface SearchRepo {
    fun getAllAccounts(): Flowable<List<Account>>
    fun deleteAccount(account: Account): Flowable<Int>
}