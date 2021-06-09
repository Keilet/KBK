package com.example.kbk.ui.dashboard.dialogs

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.AllGroup

class GroupSDialogAdapter(val groupssdialogList: ArrayList<AllGroup>, val context:Context,onClickListener:OnClickListener) : RecyclerView.Adapter<GroupSDialogAdapter.ViewHolder>() {
    interface OnClickListener{
        fun onClick(allgr: AllGroup,position: Int)
    }
    var thisClickListener:OnClickListener=onClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupSDialogAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.dialog_search_layout,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: GroupSDialogAdapter.ViewHolder, position: Int) {
        var allgr:AllGroup=groupssdialogList[position]
        holder.bindItems(groupssdialogList[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            thisClickListener.onClick(allgr,position)
        })
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return groupssdialogList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private lateinit var group:AllGroup
        fun bindItems(allgr: AllGroup) {
            this.group=allgr
            var textViewSearchD = itemView.findViewById(R.id.textViewSearchD) as TextView
            textViewSearchD.text = allgr.full_group
        }

    }

}