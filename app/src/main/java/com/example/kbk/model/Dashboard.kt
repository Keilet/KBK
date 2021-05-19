package com.example.kbk.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Dashboards")
@Parcelize
@Keep
data class Dashboard (@PrimaryKey(autoGenerate = false) val id_dashb: Int,
                      val number_cabinet:Int,
                      val id_group:Int,
                      val date_dashb:String,
                      val pair_number: Int,
                      val full_group:String,
                      val year_group: Int,
                      val shortname: String,
                      val num_course:Int,
                      val subject:String,
                      val teacher_name:String,
                      val firstname:String,
                      val lastname:String):Parcelable