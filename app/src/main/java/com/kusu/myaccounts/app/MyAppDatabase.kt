package com.kusu.myaccounts.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.myapplication.feature.login.data.local.AccountDao
import com.example.myapplication.feature.login.data.local.AccountTable

/**
 * Created by innofied on 17/7/18.
 */
@Database(entities =[(AccountTable::class)], version = 1)
abstract class MyAppDatabase: RoomDatabase() {
    abstract fun accountTableDao(): AccountDao

}
