package com.example.kbk.ui.menu.gallery

import android.content.Context
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.Teacher
import com.example.kbk.network.Constants
import com.example.kbk.ui.menu.teachers.TeachersAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

class GalleryAdapter (val imgList: ArrayList<String>,val context:Context) :
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
        Picasso.with(context).load(Constants.apilink+"images/"+imgList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return imgList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(images: String) {
           var imgView = itemView.findViewById(R.id.imageView) as ImageView

        }
    }
}