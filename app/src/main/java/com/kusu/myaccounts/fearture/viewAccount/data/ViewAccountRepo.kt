package com.kusu.myaccounts.fearture.viewAccount.data

import com.kusu.myaccounts.base.model.Account
import io.reactivex.Flowable

/**
 * Created by innofied on 24/7/18.
 */
interface ViewAccountRepo {
    fun getAccount(accId: Int): Flowable<Account>
}
