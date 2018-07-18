package com.kusu.myaccounts.base.usecase

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by innofied on 17/7/18.
 */
abstract class BaseUsecase<InputType,OutputType> {
    protected var disposables = CompositeDisposable()
    abstract fun execute(input: InputType, subscriber: DisposableSubscriber<OutputType>)

    fun cancel() {
        disposables.clear()
    }
}