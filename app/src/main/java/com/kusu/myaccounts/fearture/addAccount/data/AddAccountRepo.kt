package com.kusu.myaccounts.fearture.addAccount.data

import com.kusu.myaccounts.base.model.Account
import io.reactivex.Flowable

/**
 * Created by innofied on 23/7/18.
 */

interface AddAccountRepo {
    fun addAccount(acc:Account): Flowable<Account>
}