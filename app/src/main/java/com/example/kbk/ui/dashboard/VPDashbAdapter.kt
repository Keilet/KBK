package com.example.kbk.ui.dashboard

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kbk.StudyYear
import com.example.kbk.model.Dashboard
import java.util.*

class VPDashbAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private lateinit var dashb: Dashboard
    var dashbdates: java.util.ArrayList<String> = arrayListOf()
    private val t: StudyYear
    init {
        val d:Date= Date()
        t= StudyYear(d)
        dashbdates=t.getStringCalendar()
    }
    override fun getItemCount(): Int =this.dashbdates.size

    override fun createFragment(position: Int): Fragment = DashbFragment().apply {
        arguments = bundleOf(
            "position" to position
        )


    }
}