package com.example.kbk.model

data class GradeBook(
    val id_gbook:Int,
    val id:Int,
    val semester:Int,
    val subject:String,
    val type_sub:String,
    val idu:Int,
    val teacher_name:String,
    val firstname:String,
    val lastname:String,
    val mark:Int
)
