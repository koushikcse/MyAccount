package com.kusu.myaccounts.fearture.addAccount.presentation

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.kusu.myaccounts.R
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import kotlinx.android.synthetic.main.activity_add_account.*


/**
 * Created by innofied on 17/7/18.
 */
class AddAccountActivity : AppCompatActivity() {

    private lateinit var addAccountViewModel: AddAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).buildAddAccountComponent()
        setContentView(R.layout.activity_add_account)

        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Add Account"
        toolbar.setTitleTextColor(getColor(R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        addAccountViewModel = ViewModelProviders.of(this).get(AddAccountViewModel::class.java)

        save_btn.setOnClickListener(View.OnClickListener {
            val acc = Account()
            acc.name = accname_txt.text.toString()
            acc.key1 = key1_txt.text.toString()
            acc.key2 = key2_txt.text.toString()
            acc.key3 = key3_txt.text.toString()
            acc.value1 = value1_txt.text.toString()
            acc.value2 = value2_txt.text.toString()
            acc.value3 = value3_txt.text.toString()

            addAccountViewModel.addAccount(acc)
        })

        addAccountViewModel.isAccountAdded.observe(this, Observer { t ->
            if (t!!) {
                Toast.makeText(this, "Account Added", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_OK)
                finish()
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}