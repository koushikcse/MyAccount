package com.kusu.myaccounts.fearture.viewAccount.presenter

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.fearture.viewAccount.domain.GetAccountUsecase
import com.kusu.myaccounts.fearture.viewAccount.domain.UpdateAccountUsecase
import dagger.Lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

/**
 * Created by innofied on 20/7/18.
 */
class ViewAccountViewModel(application: Application) : AndroidViewModel(application) {
    public var account: MutableLiveData<Account>
    public var isUpdated: MutableLiveData<Boolean>

    @Inject
    lateinit var getAccountUsecase: Lazy<GetAccountUsecase>

    @Inject
    lateinit var updateAccountUsecase: Lazy<UpdateAccountUsecase>


    init {
        (application as MyApp).viewAccountComponent!!.inject(this)
        account = MutableLiveData<Account>()
        isUpdated = MutableLiveData()
    }

    internal fun getAccount(accId: Int) {
        getAccountUsecase.get().execute(
                GetAccountUsecase.Input(accId, AndroidSchedulers.mainThread()),
                UseCaseSubscriber()
        )
    }

    internal fun updateAccount(acc: Account) {
        updateAccountUsecase.get().execute(
                UpdateAccountUsecase.Input(acc, AndroidSchedulers.mainThread()),
                UpdateUseCaseSubscriber()
        )
    }


    inner class UseCaseSubscriber : DisposableSubscriber<Account>() {
        override fun onNext(t: Account?) {
            account.value = t
            Log.e("Success", "key1 : " + t?.key1 + ", value1 : " + t?.value1)
        }

        override fun onError(t: Throwable?) {
            Log.e("Error", "" + t)

        }

        override fun onComplete() {
            Log.e("Complete", "")
        }

    }

    inner class UpdateUseCaseSubscriber : DisposableSubscriber<Account>() {
        override fun onNext(t: Account?) {
            isUpdated.value = true
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