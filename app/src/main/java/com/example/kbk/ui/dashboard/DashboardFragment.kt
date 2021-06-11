package com.example.kbk.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.kbk.R
import com.example.kbk.StudyYear
import com.example.kbk.ui.dashboard.dialogs.SearchDialogFragment
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var searchd: Button
    private lateinit var viewpager_dashboard:ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewpager_dashboard=root.viewpager_dashboard
        viewpager_dashboard.adapter= VPDashbAdapter(requireActivity())
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem)=when (item.itemId) {
        R.id.action_searchdashb -> {
            val searchDialogFragment = SearchDialogFragment()
            val manager = requireFragmentManager()
            searchDialogFragment.show(manager, "searchDialog")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewpager_dashboard.adapter?.notifyDataSetChanged()
    }
}