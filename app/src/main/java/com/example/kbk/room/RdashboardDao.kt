package com.example.kbk.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RdashboardDao {
    @Query("SELECT * FROM rdashboard WHERE id_group=:id_group AND date_dashb=:date_dashb")
    fun getAll(id_group:Int,date_dashb:String): List<Rdashboard>

    @Insert
    fun insertAll(vararg rdashbs: Rdashboard)

    @Delete
    fun delete(rdashb: Rdashboard)

    @Update
    fun updateRdashboard(vararg urdashbs: Rdashboard)
}