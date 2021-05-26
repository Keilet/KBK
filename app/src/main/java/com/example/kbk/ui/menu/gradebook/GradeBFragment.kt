package com.example.kbk.ui.menu.gradebook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.EventsAdapter
import com.example.kbk.R
import com.example.kbk.model.*
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_vpdashb.*
import kotlinx.android.synthetic.main.menu_fragment_gradeb.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GradeBFragment:Fragment() {
    lateinit var rec: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.menu_fragment_gradeb, container, false)
        rec = root.findViewById(R.id.gradeb_recycler_view)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val settings: SharedPreferences =
                requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)

            val sems= listOf(1,2,3,4,5,6,7,8)
            val str: Int = sems.get(it.getInt("position"))
            semester.text = str.toString()+" семестр"

            rec.setHasFixedSize(true)
            rec.setLayoutManager(LinearLayoutManager(getActivity()));
            val retrofit = Retrofit.Builder()
                .baseUrl(ServiceBuilder.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service: Api = retrofit.create(Api::class.java)
            val call: Call<GradeBooks> =
                service.gradebookFun(
                    settings.getInt("ids", 0),
                    str
                )
            call.enqueue(object : Callback<GradeBooks> {
                override fun onResponse(call: Call<GradeBooks>, response: Response<GradeBooks>)
                {
                    var list:ArrayList<GradeBook> = response.body()!!.gradeb
                    rec.adapter = RVGradeBAdapter(list)
                }

                override fun onFailure(call: Call<GradeBooks>, t: Throwable?) {
                    Log.d("adapter",t.toString())
                }
            })
        }
    }
}