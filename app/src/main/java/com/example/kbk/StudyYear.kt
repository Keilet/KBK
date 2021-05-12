package com.example.kbk

import android.icu.util.DateInterval
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class StudyYear(val dashdate:Date) {
    val mas:ArrayList<Calendar> = arrayListOf()
    val smas:ArrayList<String> = arrayListOf()
    fun getCalendar(): ArrayList<Calendar>{
        return mas
    }
    fun getStringCalendar(): ArrayList<String>{
        return smas
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
                val s:String
                if((mas.get(mn).time.month+1)/10!=0)
                    if((mas.get(mn).time.date)/10!=0) {
                        s = (mas.get(mn).time.year + 3800).toString() + "-" + (mas.get(mn).time.month + 1).toString() + "-" + mas.get(mn).time.date.toString()
                    }
                    else
                    {
                        s = (mas.get(mn).time.year + 3800).toString() + "-" + (mas.get(mn).time.month + 1).toString() + "-0" + mas.get(mn).time.date.toString()
                    }
                else {
                    if((mas.get(mn).time.date)/10!=0) {
                        s = (mas.get(mn).time.year + 3800).toString() + "-0" + (mas.get(mn).time.month + 1).toString() + "-" + mas.get(mn).time.date.toString()
                    }
                    else
                    {
                        s = (mas.get(mn).time.year + 3800).toString() + "-0" + (mas.get(mn).time.month + 1).toString() + "-0" + mas.get(mn).time.date.toString()
                    }
                }
                Log.d("day",s)
                smas.add(mn,s)
                i.add(Calendar.DAY_OF_YEAR,1)
                mn++
            }
        }
    }

}