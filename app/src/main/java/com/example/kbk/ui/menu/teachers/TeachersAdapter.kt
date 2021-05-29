package com.example.kbk.ui.menu.teachers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.EventsAdapter
import com.example.kbk.R
import com.example.kbk.model.KBKEvent
import com.example.kbk.model.Teacher

class TeachersAdapter (val teachersList: ArrayList<Teacher>) :
    RecyclerView.Adapter<TeachersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.menu_layout_teacher,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: TeachersAdapter.ViewHolder, position: Int) {
        holder.bindItems(teachersList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return teachersList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(teacher: Teacher) {
            var textViewTeacherName = itemView.findViewById(R.id.textViewTeacherName) as TextView
            var textViewDepartment = itemView.findViewById(R.id.textViewDepartment) as TextView
            var textViewWorkDays = itemView.findViewById(R.id.textViewWorkDays) as TextView
            textViewTeacherName.text = teacher.teacher_name
            textViewDepartment.text = teacher.dep_name
            textViewWorkDays.text = "Дни работы: "+ teacher.workdays
        }
    }
}