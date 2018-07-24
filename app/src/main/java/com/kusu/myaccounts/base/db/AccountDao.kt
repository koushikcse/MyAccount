package com.example.myapplication.feature.login.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface AccountDao {
    @get:Query("SELECT * FROM AccountTable")
    val all: Flowable<List<AccountTable>>

    @Query("SELECT * FROM AccountTable WHERE accId=:accId")
    fun getAccountFromaccId(accId:Int): Flowable<AccountTable>

    @Query("SELECT * FROM AccountTable WHERE accName=:name")
    fun getAccountsFromaccName(name:String): Flowable<AccountTable>

    @Insert
    fun insertAccounts(vararg acc: AccountTable)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccounts(accountTable: AccountTable)

    @Delete
    fun deleteAccounts(accountTable: AccountTable)

}