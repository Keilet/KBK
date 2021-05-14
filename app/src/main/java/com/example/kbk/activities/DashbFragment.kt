package com.example.kbk.activities


import android.app.Activity
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
import androidx.room.Room
import com.example.kbk.*
import com.example.kbk.room.AppDatabase
import com.example.kbk.room.Rdashboard
import com.example.kbk.room.RdashboardDao
import kotlinx.android.synthetic.main.dashboardlayout.*

import kotlinx.android.synthetic.main.fragment_vpdashb.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
            val settings: SharedPreferences =
                requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
            var dashbdates: java.util.ArrayList<String> = arrayListOf()
            val d: Date = Date()
            val t: StudyYear = StudyYear(d)
            dashbdates = t.getStringCalendar()
            val str: String = dashbdates.get(it.getInt("position"))
            datedash.text = str
            rec.setHasFixedSize(true)
            rec.setLayoutManager(LinearLayoutManager(getActivity()));
            val retrofit = Retrofit.Builder()
                .baseUrl(ServiceBuilder.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: Api = retrofit.create(Api::class.java)
            val id: Int = settings.getInt("id_group", 0)
            val db = activity?.let { it1 ->
                Room.databaseBuilder(
                    it1.applicationContext,
                    AppDatabase::class.java, "database"
                ).build()
            }
            val dashbDao = db!!.dashbDao()
            GlobalScope.launch {

                val daoDB: List<Rdashboard> = dashbDao.getAll(id, datedash.text.toString())

                if (daoDB != null) {
                    var list: ArrayList<Dashboard> = arrayListOf()
                    for (i in daoDB) {
                        list.add(
                            Dashboard(
                                i.id_dashb,
                                i.number_cabinet,
                                i.id_group,
                                i.date_dashb,
                                i.pair_number,
                                i.full_group,
                                i.year_group,
                                i.shortname,
                                i.num_course,
                                i.subject,
                                i.teacher_name,
                                i.firstname,
                                i.lastname
                            )
                        );
                    }
                    rec.adapter = DashbAdapter(list)
                } else {
                    val call: Call<Dashboards> =
                        service.dashboardFun(
                            datedash.text.toString(),
                            settings.getInt("id_group", 0)
                        )

                    call.enqueue(object : Callback<Dashboards> {
                        override fun onResponse(
                            call: Call<Dashboards>,
                            response: Response<Dashboards>
                        ) {
                            var list: ArrayList<Dashboard> = response.body()!!.dashb

                            if (list != null) {
                                rec.adapter = DashbAdapter(list)
                                for (i in list) {
                                    val dbn: Rdashboard = Rdashboard(
                                        i.id_dashb,
                                        i.number_cabinet,
                                        i.id_group,
                                        i.date_dashb,
                                        i.pair_number,
                                        i.full_group,
                                        i.year_group,
                                        i.shortname,
                                        i.num_course,
                                        i.subject,
                                        i.teacher_name,
                                        i.firstname,
                                        i.lastname,
                                        false
                                    )
                                    dashbDao.insertAll(dbn)
                                }
                            }
                        }

                        override fun onFailure(call: Call<Dashboards>, t: Throwable?) {
                            Log.d("adapter", t.toString())
                        }
                    })
                }
            }
        }
    }
}