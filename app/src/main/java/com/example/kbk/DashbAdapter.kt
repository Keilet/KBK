package com.example.kbk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class DashbAdapter(val dashbList: ArrayList<Dashboard>) :
    RecyclerView.Adapter<DashbAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashbAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.dashboardlayout,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: DashbAdapter.ViewHolder, position: Int) {
        holder.bindItems(dashbList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return dashbList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(dashb: Dashboard) {
            var textViewNumberPair = itemView.findViewById(R.id.textViewNumberPair) as TextView
            var textViewStartTime = itemView.findViewById(R.id.textViewStartTime) as TextView
            var textViewEndTime = itemView.findViewById(R.id.textViewEndTime) as TextView
            var textViewSubject = itemView.findViewById(R.id.textViewSubject) as TextView
            var textViewTeacher = itemView.findViewById(R.id.textViewTeacher) as TextView
            var textViewNumberCabinet = itemView.findViewById(R.id.textViewNumberCabinet) as TextView
            textViewNumberPair.text = dashb.pair_number.toString()

            val nowDate:Date= Date()
            val d1: Time?
            val d2: Time?
            if(nowDate.day!=6) { d1=Consants.startweekDay[dashb.pair_number]
            d2=Consants.endweekDay[dashb.pair_number]}
            else {d1=Consants.shortDay[dashb.pair_number]
            d2=Consants.endshortDay[dashb.pair_number]}
            textViewStartTime.text =d1.toString()
            textViewEndTime.text =d2.toString()
            textViewSubject.text = dashb.subject
            textViewTeacher.text = dashb.teacher_name
            textViewNumberCabinet.text = dashb.number_cabinet.toString()
        }
    }
}