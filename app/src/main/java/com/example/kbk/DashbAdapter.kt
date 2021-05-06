package com.example.kbk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
            if(nowDate.day!=6) { val d:String=Consants.weekDay(dashb.pair_number)}
            textViewStartTime.text =
            textViewEndTime.text =
            textViewSubject.text = dashb.subject
            textViewTeacher.text = dashb.teacher_name
            textViewNumberCabinet.text = dashb.number_cabinet.toString()
        }
    }
}