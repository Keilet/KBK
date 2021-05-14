package com.example.kbk.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rdashboard")
data class Rdashboard(
    @PrimaryKey(autoGenerate = true) val id_dashb: Int,
    @ColumnInfo(name = "number_cabinet") val number_cabinet: Int,
    @ColumnInfo(name = "id_group") val id_group: Int,
    @ColumnInfo(name = "date_dashb") val date_dashb: String,
    @ColumnInfo(name = "pair_number") val pair_number: Int,
    @ColumnInfo(name = "full_group") val full_group: String,
    @ColumnInfo(name = "year_group") val year_group: Int,
    @ColumnInfo(name = "shortname") val shortname: String,
    @ColumnInfo(name = "num_course") val num_course: Int,
    @ColumnInfo(name = "subject") val subject: String,
    @ColumnInfo(name = "teacher_name") val teacher_name: String,
    @ColumnInfo(name = "firstname") val firstname: String,
    @ColumnInfo(name = "lastname") val lastname: String,
    @ColumnInfo(name = "isChange") val isChange: Boolean
)
