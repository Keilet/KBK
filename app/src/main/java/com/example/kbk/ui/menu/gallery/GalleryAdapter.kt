package com.example.kbk.ui.menu.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.Teacher
import com.example.kbk.ui.menu.teachers.TeachersAdapter

class GalleryAdapter (val imgList: ArrayList<String>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.menu_layout_gallery,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: GalleryAdapter.ViewHolder, position: Int) {
        holder.bindItems(imgList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return imgList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(images: String) {
/*            var imgView = itemView.findViewById(R.id.imageView) as ImageView
            textViewTeacherName.text = ima.teacher_name
            textViewDepartment.text = teacher.dep_name
            textViewWorkDays.text = "Дни работы: " + teacher.workdays*/
        }
    }
}