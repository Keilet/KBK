package com.example.kbk.activities


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.*
import kotlinx.android.synthetic.main.dashboardlayout.*

import kotlinx.android.synthetic.main.fragment_vpdashb.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class DashbFragment : Fragment() {

    private var dashbdates: ArrayList<Calendar> = arrayListOf()
    lateinit var rec: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_vpdashb, container, false)
        rec= root.findViewById(R.id.dashb_recycler_view)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val settings:SharedPreferences  = requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
            var dashbdates: java.util.ArrayList<String> = arrayListOf()
            val d:Date= Date()
            val t: StudyYear = StudyYear(d)
            dashbdates=t.getStringCalendar()
            val str:String = dashbdates.get(it.getInt("position"))
            datedash.text = str
            rec.setHasFixedSize(true)
            rec.setLayoutManager(LinearLayoutManager(getActivity()));
            val retrofit = Retrofit.Builder()
                .baseUrl(ServiceBuilder.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: Api = retrofit.create(Api::class.java)
            val id:Int=settings.getInt("id_group",0)
            val call: Call<Dashboards> = service.dashboardFun(datedash.text.toString(),settings.getInt("id_group",0))

            call.enqueue(object : Callback<Dashboards> {
                override fun onResponse(call: Call<Dashboards>, response: Response<Dashboards>)
                {
                    var list:ArrayList<Dashboard> = response.body()!!.dashb
                    rec.adapter = DashbAdapter(list)
                }

                override fun onFailure(call: Call<Dashboards>, t: Throwable?) {
                    Log.d("adapter",t.toString())
                }
            })
        }
    }
}