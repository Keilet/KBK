package com.example.kbk.ui.menu.gradebook

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.Dashboard
import com.example.kbk.model.GradeBook
import com.example.kbk.network.Constants
import com.example.kbk.ui.dashboard.DashbAdapter
import java.sql.Time
import java.util.*

data class RVGradeBAdapter(val gradebList: List<GradeBook>):
    RecyclerView.Adapter<RVGradeBAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.menu_layout_gradeb,
            parent,
            false
        )
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(gradebList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return gradebList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(gradeb: GradeBook) {

            var textViewTypeSub = itemView.findViewById(R.id.textViewTypeSub) as TextView
            var textViewSubject = itemView.findViewById(R.id.textViewSubject) as TextView
            var textViewTeacher = itemView.findViewById(R.id.textViewTeacher) as TextView
            var textViewMark = itemView.findViewById(R.id.textViewMark) as TextView

            textViewTypeSub.text = gradeb.type_sub
            textViewSubject.text = gradeb.subject
            textViewTeacher.text = gradeb.teacher_name
            textViewMark.text = gradeb.mark.toString()

        }
    }
}
