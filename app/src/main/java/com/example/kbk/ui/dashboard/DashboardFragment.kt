package com.example.kbk.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kbk.R
import com.example.kbk.StudyYear
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        root.viewpager_dashboard.adapter= VPDashbAdapter(requireActivity())

        var dashbdates: ArrayList<String> = arrayListOf()
        var i:Int=0
        val d:Date= Date()
        val t: StudyYear= StudyYear(d)
        dashbdates=t.getStringCalendar()
        var index:Int=0;
        var s:String
        for (i in dashbdates)
        {
            val z:Int=(d.month+1)/10
            if(z!=0)
            {
                if(d.date/10!=0)
                {
                    s=""+(d.year + 1900) + "-" + (d.month + 1) + "-" + d.date
                }
                else
                {
                    s=""+(d.year + 1900) + "-" + (d.month + 1) + "-0" + d.date
                }
            }
            else
            {
                if(d.date/10!=0)
                {
                    s="" + (d.year + 1900) + "-0" + (d.month + 1) + "-" + d.date
                }
                else
                {
                    s="" + (d.year + 1900) + "-0" + (d.month + 1) + "-0" + d.date
                }
            }
            Log.d("super",s)
            if(i.equals(s))
            {
                break
            }
            index++
        }
        Log.d("index",index.toString())
        root.viewpager_dashboard.setCurrentItem(index)
        return root
    }

}