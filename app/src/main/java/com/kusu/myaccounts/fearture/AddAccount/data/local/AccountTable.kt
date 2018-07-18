package com.example.myapplication.feature.login.data.local

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class AccountTable {
    @PrimaryKey
    var accId: Int? = null
    var accName: String = ""
    var accKey1: String = ""
    var accKey2: String = ""
    var accKey3: String = ""
    var accValue1: String = ""
    var accValue2: String = ""
    var accValue3: String = ""

}