package com.kusu.myaccounts.fearture.search.presentation

import android.app.Activity
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
    private val REQUEST_ADD_ACCOUNT_CODE = 1
    private val REQUEST_UPDATE_ACCOUNT_CODE = 2

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

        search_view.queryHint = "Search here.."

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        searchViewModel.getAllAccounts()

        searchViewModel.accountList.observe(this, Observer { accountlist ->
            setAccountValues(accountlist!!)
        })
    }


    fun setAccountValues(acclist: List<Account>) {
        val accountList: ArrayList<Account> = ArrayList()
        accountList.addAll(acclist)
        //adding a layoutmanager
        acc_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        searadapter = SearchAdapter(accountList, this, object : ItemClickListener {
            override fun itemDeleteClick(account: Account) {
                searchViewModel.deleteAccount(account)
            }

            override fun itemClick(account: Account) {
                startActivityForResult(Intent(this@SearchActivity, ViewAccountActivity::class.java)
                        .putExtra("Account", account), REQUEST_UPDATE_ACCOUNT_CODE)
            }
        })
        acc_list.adapter = searadapter

        search_view.setOnQueryTextListener(this)

        add_new_btn.setOnClickListener(View.OnClickListener {
            startActivityForResult(Intent(this@SearchActivity, AddAccountActivity::class.java), REQUEST_ADD_ACCOUNT_CODE)
        })

        searchViewModel.isdeleted.observe(this, Observer { t ->
            if (t!!) {
                searchViewModel.setIsDeleted()
                searchViewModel.getAllAccounts()
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ADD_ACCOUNT_CODE) {
                searchViewModel.getAllAccounts()
            } else if (requestCode == REQUEST_UPDATE_ACCOUNT_CODE) {
                searchViewModel.getAllAccounts()
            }
        }
    }
}



