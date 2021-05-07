package com.example.kbk

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kbk.activities.DashbFragment
import java.util.*

class VPDashbAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private lateinit var dashb: Dashboard
    private var dashbdates: Array<String> = arrayOf(dashb.date_dashb)
/*    private var dashbdates= Calendar(dashb.date_dashb)*/

    override fun getItemCount(): Int =dashbdates.size

    override fun createFragment(position: Int): Fragment = DashbFragment().apply {
        arguments = bundleOf(

        )
    }
}