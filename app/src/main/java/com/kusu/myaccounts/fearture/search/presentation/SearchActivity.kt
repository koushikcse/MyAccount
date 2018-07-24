package com.kusu.myaccounts.fearture.search.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.LinearLayout
import com.kusu.myaccounts.R
import com.kusu.myaccounts.app.MyApp
import com.kusu.myaccounts.base.model.Account
import com.kusu.myaccounts.fearture.addAccount.presentation.AddAccountActivity
import com.kusu.myaccounts.fearture.viewAccount.presenter.ViewAccountActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by innofied on 17/7/18.
 */
class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var searadapter: SearchAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searadapter.filter(newText!!)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).buildSearchComponent()
        setContentView(R.layout.activity_search)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        searchViewModel.getAllAccounts()

        searchViewModel.accountList.observe(this, Observer { accountlist ->
            setAccountValues(accountlist!!)
        })


        //crating an arraylist to store users using the data class user
        val accountList = ArrayList<Account>()
        val acc = Account()
        acc.id = 0
        acc.name = "facebook"
        accountList.add(acc)
        val acc1 = Account()
        acc1.id = 1
        acc1.name = "gmail"
        accountList.add(acc1)

//        accountList.add(Account(1, "facebook"))
//        accountList.add(Account(1, "facebook"))
//        accountList.add(Account(2, "gmail"))
//        accountList.add(Account(2, "gmail"))
//        accountList.add(Account(2, "gmail"))
//        accountList.add(Account(3, "basecamp"))
//        accountList.add(Account(4, "bitbucket"))
//        accountList.add(Account(4, "bitbucket"))
//        accountList.add(Account(4, "bitbucket"))
//        accountList.add(Account(4, "bitbucket"))
//        accountList.add(Account(4, "bitbucket"))
//        accountList.add(Account(4, "bitbucket"))


    }


    fun setAccountValues(acclist: List<Account>) {
        val accountList: ArrayList<Account> = ArrayList()
        accountList.addAll(acclist)
        //adding a layoutmanager
        acc_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        searadapter = SearchAdapter(accountList, this, object : ItemClickListener {
            override fun itemClick(account: Account) {
                finish()
                startActivity(Intent(this@SearchActivity, ViewAccountActivity::class.java)
                        .putExtra("Account", account))
            }
        })
        acc_list.adapter = searadapter

        search_view.setOnQueryTextListener(this)

        add_new_btn.setOnClickListener(View.OnClickListener {
            finish()
            startActivity(Intent(this@SearchActivity, AddAccountActivity::class.java))
        })
    }
}



