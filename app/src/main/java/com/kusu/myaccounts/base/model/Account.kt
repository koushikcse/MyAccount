package com.kusu.myaccounts.base.model

import java.io.Serializable

/**
 * Created by innofied on 19/7/18.
 */
data class Account(var id:Int, var name:String) : Serializable{
    private var key1:String=""
    private var key2:String=""
    private var key3:String=""
    private var value1:String=""
    private var value2:String=""
    private var value3:String=""

}
