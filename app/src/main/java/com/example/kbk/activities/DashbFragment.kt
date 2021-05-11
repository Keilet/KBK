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
import kotlinx.android.synthetic.main.fragment_vpdashb.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class DashbFragment : Fragment() {

    private var dashbdates: ArrayList<Calendar> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vpdashb, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {

            datedash.text = "Item ${it.getString("date")}"
        }
    }
}