package com.example.kbk

import android.icu.util.DateInterval
import java.util.*

data class StudyYear(val dashdate:Date) {
    fun getCalendar(): Array<Calendar>{
        val mas:Array<Calendar>
        val month:Int= Calendar.getInstance().time.month
        if(month>=1&&month<7)
        {
            val start:Calendar= Calendar.getInstance().s set(dashdate.year-1,9,1)
            val finish:Date=Date(dashdate.year,6,30)
            val i:Date=start
            while (i<=finish)
        }
        return mas
    }
}