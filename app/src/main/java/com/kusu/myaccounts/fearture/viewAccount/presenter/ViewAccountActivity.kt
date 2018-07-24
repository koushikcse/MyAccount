package com.kusu.myaccounts.fearture.viewAccount.presenter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.kusu.myaccounts.R
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import kotlinx.android.synthetic.main.activity_view_account.*

/**
 * Created by innofied on 20/7/18.
 */
class ViewAccountActivity : AppCompatActivity() {
    lateinit var account: Account
    private lateinit var viewAccountViewModel: ViewAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).buildViewAccountComponent()
        setContentView(R.layout.activity_view_account)
        viewAccountViewModel = ViewModelProviders.of(this).get(ViewAccountViewModel::class.java)

        account = intent.getSerializableExtra("Account") as Account

        viewAccountViewModel.getAccount(account.id)

        viewAccountViewModel.account.observe(this, Observer { account ->
            setAccountValues(account!!)
        })

    }

    fun setAccountValues(account: Account) {
        accname_txt.text = Editable.Factory.getInstance().newEditable(account.name)
        key1_txt.text = Editable.Factory.getInstance().newEditable(account.key1)
        value1_txt.text = Editable.Factory.getInstance().newEditable(account.value1)
        value2_txt.text = Editable.Factory.getInstance().newEditable(account.value2)
        key2_txt.text = Editable.Factory.getInstance().newEditable(account.key2)
        key3_txt.text = Editable.Factory.getInstance().newEditable(account.key3)
        value3_txt.text = Editable.Factory.getInstance().newEditable(account.value3)
    }
}