package com.example.kbk

import java.sql.Time
import java.util.*

class Consants {
    companion object {
        const val user_id="id"
        const val user_username="username"
        const val user_firstname="firstname"
        const val user_lastname="lastname"
        const val user_email="email"
        const val apilink="https://kbkapp.000webhostapp.com/"
        val weekDay:Map<Int,Time> = mapOf(1 to Time(9,0,0),2 to Time(10,30,0),3 to Time(12,20,0),4 to Time(13,50,0),5 to Time(15,20,0),6 to Time(16,50,0),7 to Time(18,20,0))
        val shortDay:Map<Int,Time> = mapOf(1 to Time(9,0,0),2 to Time(10,30,0),3 to Time(12,0,0),4 to Time(13,30,0),5 to Time(15,0,0),6 to Time(16,30,0),7 to Time(18,0,0))
    }

}