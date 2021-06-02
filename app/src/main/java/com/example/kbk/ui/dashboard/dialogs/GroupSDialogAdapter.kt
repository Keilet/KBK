package com.example.kbk.ui.dashboard.dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.AllGroup

class GroupSDialogAdapter(val groupssdialogList: ArrayList<AllGroup>) : RecyclerView.Adapter<GroupSDialogAdapter.ViewHolder>() {

    internal interface OnStateClickListener {
        fun onStateClick(gr: AllGroup, position: Int)
    }

    //private val onClickListener: OnStateClickListener? = null


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
//        holder.itemView.setOnClickListener {
//            onClickListener!!.onStateClick(
//                allgr,
//                position
//            )
//        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return groupssdialogList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(allgr: AllGroup) {
            var textViewSearchD = itemView.findViewById(R.id.textViewSearchD) as TextView
            textViewSearchD.text = allgr.full_group
        }
    }
}