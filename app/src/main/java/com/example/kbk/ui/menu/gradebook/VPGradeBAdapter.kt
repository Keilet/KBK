package com.example.kbk.ui.menu.gradebook

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kbk.StudyYear
import com.example.kbk.model.Dashboard
import com.example.kbk.model.GradeBook
import com.example.kbk.ui.dashboard.DashbFragment
import java.util.*

class VPGradeBAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    //private lateinit var gradeb: GradeBook


    override fun getItemCount(): Int = 8

    override fun createFragment(position: Int): Fragment = GradeBFragment().apply {
        arguments = bundleOf(
            "position" to position
        )


    }
}