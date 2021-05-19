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
import io.reactivex.schedulers.Schedulers

class DashbFragment : Fragment()
{
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
                    DashboardDatabase::class.java, "database-name"
                ).build()
            }
            var curDash:List<Dashboard> = arrayListOf()
            Observable.fromCallable({
                curDash = db?.getDashboardDao()?.getDashboards(id,datedash.text.toString())!!;

            }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

            if(curDash!=null)
                rec.adapter = DashbAdapter(curDash)

            val call: Call<Dashboards> =
                        service.dashboardFun(
                            datedash.text.toString(),
                            settings.getInt("id_group", 0)
                        )

            call.enqueue(object : Callback<Dashboards> {
                        override fun onResponse(
                            call: Call<Dashboards>,
                            response: Response<Dashboards>
                        )
                        {
                            var list: ArrayList<Dashboard> = response.body()!!.dashb

                            if (list != null) {
                                rec.adapter = DashbAdapter(list)
                                runBlocking {
                                    db?.getDashboardDao()?.addDashboard(list);
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