package com.example.kbk.ui.menu.teachers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.EventsAdapter
import com.example.kbk.R
import com.example.kbk.model.KBKEvent
import com.example.kbk.model.KBKEvents
import com.example.kbk.model.Teacher
import com.example.kbk.model.Teachers
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AllTeachers: AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_allteachers)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        val rec: RecyclerView = findViewById(R.id.teacher_recycler_view)
        rec.setHasFixedSize(true)
        rec.setLayoutManager(LinearLayoutManager(this))
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        rec.layoutManager = LinearLayoutManager(this)


        //crating an arraylist to store users using the data class user
        //val eve = ArrayList<KBKEvent>()

        val call: Call<Teachers> = service.allteachers()

        call.enqueue(object : Callback<Teachers> {
            override fun onResponse(call: Call<Teachers>, response: Response<Teachers>)
            {
                var list:ArrayList<Teacher> = response.body()!!.teachers
                rec.adapter = TeachersAdapter(list)
            }

            override fun onFailure(call: Call<Teachers>, t: Throwable?) {
                Log.d("adapter",t.toString())
            }
        })
    }
}