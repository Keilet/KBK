package com.example.kbk.db;


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kbk.model.Dashboard

@Dao
interface DashboardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDashboard(dashboards:ArrayList<Dashboard>?)

    @Query("SELECT * FROM dashboards WHERE id_group=:id and date_dashb=:day")
    fun getDashboards(id:Int,day:String) : List<Dashboard>

//    для преподавателя
    @Query("SELECT * FROM dashboards WHERE idu=:idu and date_dashb=:day")
    fun getDashboards2(idu:Int,day:String) : List<Dashboard>

//    @Query("SELECT * FROM dashboards")
//    fun getDashboards() : List<Dashboard>
}
