package com.example.kbk.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.model.Dashboard
import com.example.kbk.R
import com.example.kbk.network.Constants
import java.sql.Time
import java.util.*

data class DashbAdapter(val dashbList: List<Dashboard>) :
    RecyclerView.Adapter<DashbAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.dashboardlayout,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
            textViewNumberPair.text = dashb.pair_number.toString()+" пара"

            val nowDate:Date= Date()
            val d1: Time?
            val d2: Time?
            if(nowDate.day!=6) { d1= Constants.startweekDay[dashb.pair_number]
            d2= Constants.endweekDay[dashb.pair_number]}
            else {d1= Constants.shortDay[dashb.pair_number]
            d2= Constants.endshortDay[dashb.pair_number]}
            textViewStartTime.text =d1.toString().substring(0,d1.toString().length-3)
            textViewEndTime.text =d2.toString().substring(0,d2.toString().length-3)
            textViewSubject.text = dashb.subject
            textViewTeacher.text = dashb.teacher_name
            textViewNumberCabinet.text = dashb.number_cabinet.toString()
        }
    }
}