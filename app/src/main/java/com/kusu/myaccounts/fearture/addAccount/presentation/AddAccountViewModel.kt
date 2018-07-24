package com.kusu.myaccounts.fearture.addAccount.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.fearture.addAccount.domain.AddAccountUsecase
import dagger.Lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Created by innofied on 17/7/18.
 */
class AddAccountViewModel(application: Application) : AndroidViewModel(application) {
    private var account: MutableLiveData<Account>
    public var isAccountAdded: MutableLiveData<Boolean>

    @Inject
    lateinit var addAccountUsecase: Lazy<AddAccountUsecase>

    init {
        (application as MyApp).addAccountComponent!!.inject(this)
        account = MutableLiveData<Account>()
        isAccountAdded=MutableLiveData()
    }

    internal fun addAccount(account: Account) {
        addAccountUsecase.get().execute(
                AddAccountUsecase.Input(account, AndroidSchedulers.mainThread()),
                UseCaseSubscriber()
        )

    }

    inner class UseCaseSubscriber : DisposableSubscriber<Account>() {
        override fun onNext(t: Account?) {
            isAccountAdded.value=true
            Log.e("Success", "key1 : " + t?.key1 + ", value1 : " + t?.value1)

        }

        override fun onError(t: Throwable?) {
            Log.e("Error", "" + t)

        }

        override fun onComplete() {
            Log.e("Complete", "")
        }

    }

}