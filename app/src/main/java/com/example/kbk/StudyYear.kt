package com.example.kbk

import android.icu.util.DateInterval
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class StudyYear(val dashdate:Date) {
    val mas:ArrayList<Calendar> = arrayListOf()

    fun getCalendar(): ArrayList<Calendar>{
        return mas
    }

    init {
        val d:Date= Date()
        val month:Int=d.month
        if(month>=0&&month<6)
        {
            val start:Calendar= Calendar.getInstance()
            start.set(Calendar.YEAR,dashdate.year-1)
            start.set(Calendar.MONTH,8)
            start.set(Calendar.DAY_OF_MONTH,1)
            val finish:Calendar= Calendar.getInstance()
            finish.set(Calendar.YEAR,dashdate.year)
            finish.set(Calendar.MONTH,5)
            finish.set(Calendar.DAY_OF_MONTH,30)
            val i:Calendar=start
            var mn:Int=0
            while (i<=finish)
            {
                mas.add(mn,i)
                Log.d("day",mas.get(mn).time.year.toString()+":"+mas.get(mn).time.month.toString()+":"+mas.get(mn).time.date.toString())
                i.add(Calendar.DAY_OF_YEAR,1)
                mn++
            }
        }
    }

}