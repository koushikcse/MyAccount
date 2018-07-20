package com.kusu.myaccounts.fearture.search.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.widget.LinearLayout
import com.kusu.myaccounts.R
import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.fearture.addAccount.presentation.AddAccountActivity
import com.kusu.myaccounts.fearture.viewAccount.presenter.ViewAccountActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by innofied on 17/7/18.
 */
class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var searadapter: SearchAdapter
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searadapter.filter(newText!!)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        //adding a layoutmanager
        acc_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        //crating an arraylist to store users using the data class user
        val accountList = ArrayList<Account>()
        accountList.add(Account(1, "facebook"))
        accountList.add(Account(1, "facebook"))
        accountList.add(Account(1, "facebook"))
        accountList.add(Account(1, "facebook"))
        accountList.add(Account(2, "gmail"))
        accountList.add(Account(2, "gmail"))
        accountList.add(Account(2, "gmail"))
        accountList.add(Account(3, "basecamp"))
        accountList.add(Account(4, "bitbucket"))
        accountList.add(Account(4, "bitbucket"))
        accountList.add(Account(4, "bitbucket"))
        accountList.add(Account(4, "bitbucket"))
        accountList.add(Account(4, "bitbucket"))
        accountList.add(Account(4, "bitbucket"))

        searadapter = SearchAdapter(accountList, this, object : ItemClickListener {
            override fun itemClick(account: Account) {
                startActivity(Intent(this@SearchActivity, ViewAccountActivity::class.java)
                        .putExtra("Account", account))
                finish()
            }
        })
        acc_list.adapter = searadapter

        search_view.setOnQueryTextListener(this)
    }
}



