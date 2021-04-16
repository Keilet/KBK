package com.example.kbk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(val kbkeventList: List<KBKEvent>): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.eventlist_layout,
            parent,
            false
        )
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: EventsAdapter.ViewHolder, position: Int) {
        holder.bindItems(kbkeventList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return kbkeventList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(kbkevent: KBKEvent) {
            val textViewTitle = itemView.findViewById(R.id.textViewTitleEvent) as TextView
            val textViewDate = itemView.findViewById(R.id.textViewDateEvent) as TextView
            val textViewContent  = itemView.findViewById(R.id.textViewContentEvent) as TextView
            textViewTitle.text = kbkevent.event_title
            textViewDate.text = kbkevent.event_send_date.toString()
            textViewContent.text = kbkevent.event_content
        }
    }
}