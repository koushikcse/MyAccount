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
 * Created by innofied on 24/7/18.
 */
class SearchUsecase @Inject constructor (private val repo: SearchRepo) : BaseUsecase<SearchUsecase.Input, List<Account>>(){

    override fun execute(input: Input, subscriber: DisposableSubscriber<List<Account>>) {
        Flowable.just(input)
                .flatMap { t -> repo.getAllAccounts() }
                .subscribeOn(Schedulers.newThread())
                .observeOn(input.observerOnScheduler)
                .subscribe(subscriber)

        disposables.add(subscriber)
    }

    class Input(val observerOnScheduler: Scheduler)
}