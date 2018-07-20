package com.kusu.myaccounts.fearture.viewAccount.presenter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kusu.myaccounts.R
import com.kusu.myaccounts.base.model.Account

/**
 * Created by innofied on 20/7/18.
 */
class ViewAccountActivity : AppCompatActivity() {
    lateinit var account: Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_account)
        account = intent.getSerializableExtra("Account") as Account


    }
}