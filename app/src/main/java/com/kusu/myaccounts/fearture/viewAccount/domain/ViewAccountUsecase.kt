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
 * Created by innofied on 24/7/18.
 */
class ViewAccountUsecase @Inject constructor (private val repo: ViewAccountRepo) : BaseUsecase<ViewAccountUsecase.Input, Account>(){
    override fun execute(input: Input, subscriber: DisposableSubscriber<Account>) {
        Flowable.just(input.accId)
                .flatMap<Account> { accId -> repo.getAccount(accId) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(input.observerOnScheduler)
                .subscribe(subscriber)

        disposables.add(subscriber)
    }

    class Input(val accId: Int, val observerOnScheduler: Scheduler)
}