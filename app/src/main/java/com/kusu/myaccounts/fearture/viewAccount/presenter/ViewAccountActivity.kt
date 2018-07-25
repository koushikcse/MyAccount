package com.kusu.myaccounts.fearture.viewAccount.presenter

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.kusu.myaccounts.R
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import kotlinx.android.synthetic.main.activity_view_account.*

/**
 * Created by innofied on 20/7/18.
 */
class ViewAccountActivity : AppCompatActivity() {
    private lateinit var account: Account
    private lateinit var viewAccountViewModel: ViewAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).buildViewAccountComponent()
        setContentView(R.layout.activity_view_account)

        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Account Details"
        toolbar.setTitleTextColor(getColor(R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        viewAccountViewModel = ViewModelProviders.of(this).get(ViewAccountViewModel::class.java)

        account = intent.getSerializableExtra("Account") as Account

        viewAccountViewModel.getAccount(account.id)

        viewAccountViewModel.account.observe(this, Observer { acc ->
            this.account = acc!!
            setAccountValues(acc!!)
        })

        viewAccountViewModel.isUpdated.observe(this, Observer { t ->
            if (t!!) {
                Toast.makeText(this, "Account Updated", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_OK)
                finish()
            }
        })

        update_btn.setOnClickListener(View.OnClickListener {
            val acc = Account()
            acc.id = account.id
            acc.name = accname_txt.text.toString()
            acc.key1 = key1_txt.text.toString()
            acc.key2 = key2_txt.text.toString()
            acc.key3 = key3_txt.text.toString()
            acc.value1 = value1_txt.text.toString()
            acc.value2 = value2_txt.text.toString()
            acc.value3 = value3_txt.text.toString()

            viewAccountViewModel.updateAccount(acc)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}