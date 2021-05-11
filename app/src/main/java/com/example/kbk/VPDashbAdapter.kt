package com.example.kbk

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kbk.activities.DashbFragment
import java.util.*
import kotlin.collections.ArrayList

class VPDashbAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private lateinit var dashb: Dashboard
    private var dashbdates: ArrayList<Calendar> = arrayListOf()
    private val t:StudyYear
    var i:Int=0
    init {
        val d:Date= Date()
        t= StudyYear(d)
        dashbdates=t.getCalendar()
        val now:Calendar= Calendar.getInstance()
        now.set(d.year,d.month,d.date)

        for (n in dashbdates)
        {
            if (n.equals(now))
            {
                break
            }
            i++
        }

    }
    override fun getItemCount(): Int =this.dashbdates.size

    override fun createFragment(position: Int): Fragment = DashbFragment().apply {
        arguments = bundleOf(
            "position" to position
//            "date" to dashbdates.get(t.getIndex()).time.toString()
        )


    }
}