package com.example.kbk.activities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.Api

import com.example.kbk.R
import com.example.kbk.ServiceBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashbFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_vpdashb, container, false)
        val rec: RecyclerView = root.findViewById(R.id.dashb_recycler_view)
        rec.setHasFixedSize(true);
        rec.setLayoutManager(LinearLayoutManager(getActivity()));
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        rec.layoutManager = LinearLayoutManager(context)

        return root
    }
}