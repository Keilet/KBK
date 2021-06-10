package com.example.kbk.ui.dashboard


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.kbk.*
import com.example.kbk.db.DashboardDatabase
import com.example.kbk.model.Dashboard
import com.example.kbk.model.Dashboards
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_vpdashb.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DashbFragment : Fragment() {
    private var dashbdates: ArrayList<Calendar> = arrayListOf()
    lateinit var rec: RecyclerView
    private lateinit var s: String
    private lateinit var contextThis: Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_vpdashb, container, false)
        rec = root.findViewById(R.id.dashb_recycler_view)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val settings: SharedPreferences =
                requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
            var dashbdates: java.util.ArrayList<String> = arrayListOf()
            val d: Date = Date()
            val t: StudyYear = StudyYear(d)
            contextThis= requireActivity()
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
            val idu: Int = settings.getInt("idu", 0)
            val idsgroup: Int = settings.getInt("id_searchgroup", 0)
            val idsteacher: Int = settings.getInt("id_searchteacher", 0)


            if (id != 0 && idsgroup==0) {
                val db = activity?.let { it1 ->
                    Room.databaseBuilder(
                        it1.applicationContext,
                        DashboardDatabase::class.java, "database-name"
                    ).build()
                }
                var curDash: List<Dashboard> = arrayListOf()

                Observable.fromCallable({
                    curDash = db?.getDashboardDao()?.getDashboards(id, datedash.text.toString())!!;
                }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        Consumer {
                            val z: Int = (d.month + 1) / 10
                            if (z != 0) {
                                if (d.date / 10 != 0) {
                                    s = "" + (d.year + 1900) + "-" + (d.month + 1) + "-" + d.date
                                } else {
                                    s = "" + (d.year + 1900) + "-" + (d.month + 1) + "-0" + d.date
                                }
                            } else {
                                if (d.date / 10 != 0) {
                                    s = "" + (d.year + 1900) + "-0" + (d.month + 1) + "-" + d.date
                                } else {
                                    s = "" + (d.year + 1900) + "-0" + (d.month + 1) + "-0" + d.date
                                }
                            }
                            val inner: ArrayList<Dashboard> = arrayListOf()
                            for (i in curDash) {
                                if (i.date_dashb < s) inner.add(i)
                            }
                            rec.adapter = DashbAdapter(inner,contextThis)
                        }
                    )


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
                            rec.adapter = DashbAdapter(list,contextThis)
                            for (i in list) {
                                if (i.date_dashb.equals(s)) {
                                    runBlocking {
                                        db?.getDashboardDao()?.addDashboard(list);
                                    }
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<Dashboards>, t: Throwable?) {
                        Log.d("adapter", t.toString())
                    }
                })
            }
            if (idu != 0 && idsgroup==0) {
                val db = activity?.let { it1 ->
                    Room.databaseBuilder(
                        it1.applicationContext,
                        DashboardDatabase::class.java, "database-name"
                    ).build()
                }
                var curDash: List<Dashboard> = arrayListOf()

                Observable.fromCallable({
                    curDash = db?.getDashboardDao()?.getDashboards2(idu, datedash.text.toString())!!;
                }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        Consumer {
                            val z: Int = (d.month + 1) / 10
                            if (z != 0) {
                                if (d.date / 10 != 0) {
                                    s = "" + (d.year + 1900) + "-" + (d.month + 1) + "-" + d.date
                                } else {
                                    s = "" + (d.year + 1900) + "-" + (d.month + 1) + "-0" + d.date
                                }
                            } else {
                                if (d.date / 10 != 0) {
                                    s = "" + (d.year + 1900) + "-0" + (d.month + 1) + "-" + d.date
                                } else {
                                    s = "" + (d.year + 1900) + "-0" + (d.month + 1) + "-0" + d.date
                                }
                            }
                            val inner: ArrayList<Dashboard> = arrayListOf()
                            for (i in curDash) {
                                if (i.date_dashb < s) inner.add(i)
                            }
                            rec.adapter = DashbAdapter(inner,contextThis)
                        }
                    )


                val call: Call<Dashboards> =
                    service.dashboard2Fun(
                        datedash.text.toString(),
                        settings.getInt("idu", 0)
                    )

                call.enqueue(object : Callback<Dashboards> {
                    override fun onResponse(
                        call: Call<Dashboards>,
                        response: Response<Dashboards>
                    ) {
                        var list: ArrayList<Dashboard> = response.body()!!.dashb

                        if (list != null) {
                            rec.adapter = DashbAdapter(list,contextThis)
                            for (i in list) {
                                if (i.date_dashb.equals(s)) {
                                    runBlocking {
                                        db?.getDashboardDao()?.addDashboard(list);
                                    }
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<Dashboards>, t: Throwable?) {
                        Log.d("adapter", t.toString())
                    }
                })
            }





            if (idsgroup != 0) {
                var id_searchteacher: SharedPreferences.Editor=settings.edit()
                id_searchteacher.putInt("id_searchteacher",0)
                id_searchteacher.apply()
                //list.clear()
                rec.adapter!!.notifyDataSetChanged()

                val call: Call<Dashboards> =
                    service.dashboardFun(
                        datedash.text.toString(),
                        settings.getInt("id_searchgroup", 0)
                    )

                call.enqueue(object : Callback<Dashboards> {
                    override fun onResponse(
                        call: Call<Dashboards>,
                        response: Response<Dashboards>
                    ) {
                        var list: ArrayList<Dashboard> = response.body()!!.dashb
                        if (list != null) {
                            rec.adapter = DashbAdapter(list,contextThis)
                        }
                    }

                    override fun onFailure(call: Call<Dashboards>, t: Throwable?) {
                        Log.d("adapter", t.toString())
                    }
                })
            }


            if (idsteacher != 0) {
                var id_searchgroup: SharedPreferences.Editor=settings.edit()
                id_searchgroup.putInt("id_searchgroup",0)
                id_searchgroup.apply()
                //list.clear()
                rec.adapter!!.notifyDataSetChanged()

                val call: Call<Dashboards> =
                    service.dashboard2Fun(
                        datedash.text.toString(),
                        settings.getInt("id_searchteacher", 0)
                    )

                call.enqueue(object : Callback<Dashboards> {
                    override fun onResponse(
                        call: Call<Dashboards>,
                        response: Response<Dashboards>
                    ) {
                        var list: ArrayList<Dashboard> = response.body()!!.dashb
                        if (list != null) {
                            rec.adapter = DashbAdapter(list,contextThis)
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