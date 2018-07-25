package com.kusu.myaccounts.fearture.viewAccount.domain

import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.base.usecase.BaseUsecase
import com.kusu.myaccounts.fearture.viewAccount.data.ViewAccountRepo
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Created by innofied on 25/7/18.
 */
class UpdateAccountUsecase @Inject constructor (private val repo: ViewAccountRepo) : BaseUsecase<UpdateAccountUsecase.Input, Account>(){
    override fun execute(input: UpdateAccountUsecase.Input, subscriber: DisposableSubscriber<Account>) {
        Flowable.just(input.acc)
                .flatMap<Account> { acc -> repo.updateAccount(acc) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(input.observerOnScheduler)
                .subscribe(subscriber)

        disposables.add(subscriber)
    }

    class Input(val acc: Account, val observerOnScheduler: Scheduler)
}