package com.example.kbk.network

import java.sql.Time

class Constants {
    companion object {
        const val user_id="id"
        const val user_username="username"
        const val user_firstname="firstname"
        const val user_lastname="lastname"
        const val user_email="email"
        const val apilink="https://kbkapp.000webhostapp.com/"
        val startweekDay:Map<Int,Time> = mapOf(1 to Time(9,0,0),2 to Time(10,30,0),3 to Time(12,20,0),4 to Time(13,50,0),5 to Time(15,20,0),6 to Time(16,50,0),7 to Time(18,20,0))
        val endweekDay:Map<Int,Time> = mapOf(1 to Time(10,20,0),2 to Time(11,50,0),3 to Time(13,40,0),4 to Time(15,10,0),5 to Time(16,40,0),6 to Time(18,10,0),7 to Time(19,40,0))
        val shortDay:Map<Int,Time> = mapOf(1 to Time(9,0,0),2 to Time(10,30,0),3 to Time(12,0,0),4 to Time(13,30,0),5 to Time(15,0,0),6 to Time(16,30,0),7 to Time(18,0,0))
        val endshortDay:Map<Int,Time> = mapOf(1 to Time(10,20,0),2 to Time(11,50,0),3 to Time(13,20,0),4 to Time(14,50,0),5 to Time(16,20,0),6 to Time(17,50,0),7 to Time(19,20,0))
    }

}