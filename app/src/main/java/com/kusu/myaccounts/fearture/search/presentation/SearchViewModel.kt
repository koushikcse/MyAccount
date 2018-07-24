package com.kusu.myaccounts.fearture.search.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.fearture.search.domain.SearchUsecase
import dagger.Lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Created by innofied on 24/7/18.
 */
class SearchViewModel(application: Application) : AndroidViewModel(application) {
    public var accountList: MutableLiveData<List<Account>>

    @Inject
    lateinit var searchUsecase: Lazy<SearchUsecase>

    init {
        (application as MyApp).searchComponent!!.inject(this)
        accountList = MutableLiveData<List<Account>>()
    }

    internal fun getAllAccounts() {
        searchUsecase.get().execute(
                SearchUsecase.Input(AndroidSchedulers.mainThread()),
                UseCaseSubscriber()
        )

    }


    inner class UseCaseSubscriber : DisposableSubscriber<List<Account>>() {
        override fun onNext(t: List<Account>?) {
            accountList.value = t
            Log.e("Success", "response")
        }

        override fun onError(t: Throwable?) {
            Log.e("Error", "" + t)

        }

        override fun onComplete() {
            Log.e("Complete", "")
        }

    }
}