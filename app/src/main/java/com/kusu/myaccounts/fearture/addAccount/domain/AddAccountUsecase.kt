package com.kusu.myaccounts.fearture.addAccount.domain

import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.base.usecase.BaseUsecase
import com.kusu.myaccounts.fearture.addAccount.data.AddAccountRepo
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Created by innofied on 17/7/18.
 */
class AddAccountUsecase @Inject constructor (private val repo: AddAccountRepo) : BaseUsecase<AddAccountUsecase.Input, Account>(){
    override fun execute(input: Input, subscriber: DisposableSubscriber<Account>) {
        Flowable.just(input.account)
                .flatMap<Account> { account -> repo.addAccount(account) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(input.observerOnScheduler)
                .subscribe(subscriber)

        disposables.add(subscriber)
    }

    class Input(val account: Account, val observerOnScheduler: Scheduler)
}