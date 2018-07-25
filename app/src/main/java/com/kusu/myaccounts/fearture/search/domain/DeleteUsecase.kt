package com.kusu.myaccounts.fearture.search.domain

import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.base.usecase.BaseUsecase
import com.kusu.myaccounts.fearture.search.data.SearchRepo
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Created by innofied on 25/7/18.
 */
class DeleteUsecase @Inject constructor(private val repo: SearchRepo) : BaseUsecase<DeleteUsecase.Input, Int>() {
    override fun execute(input: DeleteUsecase.Input, subscriber: DisposableSubscriber<Int>) {
        Flowable.just(input.acc)
                .flatMap { acc -> repo.deleteAccount(acc) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(input.observerOnScheduler)
                .subscribe(subscriber)

        disposables.add(subscriber)    }


    class Input(val acc: Account, val observerOnScheduler: Scheduler)
}