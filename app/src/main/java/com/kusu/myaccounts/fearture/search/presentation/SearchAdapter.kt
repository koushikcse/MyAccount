package com.kusu.myaccounts.fearture.search.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusu.myaccounts.R
import com.kusu.myaccounts.base.model.Account
import kotlinx.android.synthetic.main.item_search_list.view.*

/**
 * Created by innofied on 19/7/18.
 */
class SearchAdapter(val items: ArrayList<Account>, val context: Context, itemClickListener: ItemClickListener) : RecyclerView.Adapter<ViewHolder>() {
    var dataitem: ArrayList<Account> = ArrayList()
    lateinit var itemClickListener: ItemClickListener

    init {
        dataitem.addAll(items)
        this.itemClickListener = itemClickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dataitem.get(position),itemClickListener)
    }

    override fun getItemCount(): Int {
        return dataitem.size
    }

    fun filter(query: String) {
        dataitem.clear()
        if (query.length == 0) {
            dataitem.addAll(items)
        } else {
            for (item: Account in items) {
                if (item.name.startsWith(query)) {
                    dataitem.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

}

interface ItemClickListener {
    fun itemClick(account: Account)
}

class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


    fun bindItems(account: Account,itemClickListener: ItemClickListener) {
//        itemView.findViewById<TextView>(R.id.acc_name_txt).text = account.name
        itemView.acc_name_txt.text = account.name
        itemView.item_row.setOnClickListener {
            itemClickListener.itemClick(account)
        }
    }
}