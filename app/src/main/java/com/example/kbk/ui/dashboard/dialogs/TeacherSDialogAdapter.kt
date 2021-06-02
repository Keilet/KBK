package com.example.kbk.ui.dashboard.dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.Teacher
import com.example.kbk.ui.menu.teachers.TeachersAdapter

class TeacherSDialogAdapter (val teachersdialogList: ArrayList<Teacher>) : RecyclerView.Adapter<TeacherSDialogAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherSDialogAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.dialog_search_layout,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: TeacherSDialogAdapter.ViewHolder, position: Int) {
        holder.bindItems(teachersdialogList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return teachersdialogList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(teacher: Teacher) {
            var textViewSearchD = itemView.findViewById(R.id.textViewSearchD) as TextView
            textViewSearchD.text = teacher.teacher_name
        }
    }
}