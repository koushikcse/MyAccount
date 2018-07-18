package com.example.myapplication.feature.login.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import android.arch.persistence.room.OnConflictStrategy

@Dao
interface AccountDao {
    @get:Query("SELECT * FROM AccountTable")
    val all: Flowable<List<AccountTable>>

    @Query("SELECT * FROM AccountTable WHERE accId=:accId")
    fun getAccountFromaccId(accId:Int): Flowable<AccountTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccounts(vararg acc: AccountTable)


}